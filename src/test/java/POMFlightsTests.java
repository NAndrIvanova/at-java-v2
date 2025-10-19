import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.FlightsFoundList;
import pages.FlightsLoginPage;
import pages.FlightsSearchPage;
import pages.RegistrationPage;
import pages.SearchResultPage;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    void setUp() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        getWebDriver().manage().window().maximize();
    }

    //Тест-кейсы

    @Test
    @DisplayName("POM-01. Неуспешный логин")
    void test01() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "wrong_stand_pass1");
        login_page.verify_wrong_username_or_password();
    }

    @Test
    @DisplayName("POM-02. Не задана дата вылета")
    void test02() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Париж", "");
        searchPage.verifyEmptyDate();
    }

    @Test
    @DisplayName("POM-03. Не найдены рейсы")
    void test03() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Казань","Париж", "17.11.2025");

        SearchResultPage search_result_page = new SearchResultPage();
        search_result_page.verify_no_flights();
    }

    @Test
    @DisplayName("POM-04. Регистрация - некорректно заполнен номер паспорта")
    void test04() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();

        FlightsSearchPage search_page = new FlightsSearchPage();
        search_page.findFlights("Москва", "Лондон", "20-10-2025");

        SearchResultPage search_result_page = new SearchResultPage();
        search_result_page.verify_flights_exists();
        search_result_page.register();

        RegistrationPage registration_page = new RegistrationPage();
        registration_page.fill_form(null, "Номер паспорта", null, null);
        registration_page.finish_registration_wrong_passport_number();
    }
    //Домашняя работа Занятие-4
    @Test
    @DisplayName("POM-05. Проверить вход с пустыми полями")
    void test05() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("", "");
        login_page.verify_wrong_empty();
    }

    @Test
    @DisplayName("POM-06. Проверить поиск с прошедшей датой")
    void test06() {
        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.login("standard_user", "stand_pass1");

        FlightsSearchPage searchPage = new FlightsSearchPage();
        searchPage.findFlights("Москва","Париж", "01-01-2025");
        searchPage.verifyOldDate();
    }

    @Test
    @DisplayName("POM-07. Проверить поиск с валидными данными")
    void test07() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();
        FlightsSearchPage search_page = new FlightsSearchPage();
        search_page.findFlights("Москва", "Лондон", "20-10-2025");
        SearchResultPage search_result_page = new SearchResultPage();
        search_result_page.verify_flights_exists();
        search_result_page.register();
    }
    @Test
    @DisplayName("POM-08. Проверить кнопку 'Новый поиск'")
    void test08() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();
        FlightsSearchPage search_page = new FlightsSearchPage();
        search_page.findFlights("Москва", "Лондон", "20-10-2025");
        SearchResultPage search_result_page = new SearchResultPage();
        search_result_page.verify_search_button_text();
    }

    @Test
    @DisplayName("POM-9. Проверить автозаполнение данных пользователя")
    void test09() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();
        FlightsSearchPage search_page = new FlightsSearchPage();
        search_page.findFlights("Москва", "Лондон", "20-10-2025");
        SearchResultPage search_result_page = new SearchResultPage();
        search_result_page.verify_flights_exists();
        search_result_page.register();
        RegistrationPage registration_page = new RegistrationPage();
        registration_page.verify_autofilled_data();
        registration_page.finish_registration_success();
    }
    @Test
    @DisplayName("POM-10. Проверить отправку формы с пустыми полями")
    void test10() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();
        FlightsSearchPage search_page = new FlightsSearchPage();
        search_page.findFlights("Москва", "Лондон", "20-10-2025");
        SearchResultPage search_result_page = new SearchResultPage();
        search_result_page.verify_flights_exists();
        search_result_page.register();
        RegistrationPage registration_page = new RegistrationPage();
        registration_page.clear_form();
    }
    @Test
    @DisplayName("POM-11. Проверить кнопку 'Вернуться к найденным рейсам'")
    void test11() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();
        FlightsSearchPage search_page = new FlightsSearchPage();
        search_page.findFlights("Москва", "Лондон", "20-10-2025");
        SearchResultPage search_result_page = new SearchResultPage();
        search_result_page.verify_flights_exists();
        search_result_page.register();
        search_result_page.verify_back_to_flights_button_exists();
        search_result_page.verify_back_to_flights_button_text();
        search_result_page.click_back_to_flights_button();
        search_result_page.verify_flights_table_displayed();
    }
}

