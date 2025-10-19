package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {
    SelenideElement FlightsTable, RegistrationButton, NoFlightsMessage, SearchButton, backToFlightsButton;
    public SearchResultPage() {
        FlightsTable = $x("//table[@id='flightsTable']");
        RegistrationButton = FlightsTable.find(By.xpath(".//button[text()='Зарегистрироваться']"));
        NoFlightsMessage = FlightsTable.find(By.xpath(".//td[@colspan='7']"));
        SearchButton = $(".new-search-btn");
        backToFlightsButton = $x("//button[contains(text(), 'Вернуться к найденным рейсам')]");
    }
    public void verify_flights_exists() {
        RegistrationButton.shouldBe(exist);
    }
    public void verify_no_flights() {
        NoFlightsMessage.shouldHave(text("Рейсов по вашему запросу не найдено."));
    }
    public void register() {
        RegistrationButton.click();
    }
    public void verify_search_button_text() {
        SearchButton.shouldHave(text("Новый поиск"));
    }
    public void verify_back_to_flights_button_exists() {
        backToFlightsButton.shouldBe(visible);
    }

    public void verify_back_to_flights_button_text() {
        backToFlightsButton.shouldHave(text("Вернуться к найденным рейсам"));
    }

    public void click_back_to_flights_button() {
        backToFlightsButton.click();
    }

    public void verify_flights_table_displayed() {
        FlightsTable.shouldBe(visible);
    }
}
