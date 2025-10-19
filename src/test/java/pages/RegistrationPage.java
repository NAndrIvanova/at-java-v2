package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage {
    SelenideElement PassengerName, PassportNumber, Email, Phone, CompleteRegistrationButton, ReturnButton, RegistrationMessage;
    Alert RegistrationFinishedAlert;
    public RegistrationPage() {
        PassengerName = $("#passengerName");
        PassportNumber = $("#passportNumber");
        Email = $("#email"); Phone = $("#phone");
        CompleteRegistrationButton = $x("//button[text()='Завершить регистрацию']");
        ReturnButton = $x("//button[text()='Вернуться к найденным рейсам']");
        RegistrationMessage = $("#registrationMessage");
    }
    public void finish_registration_success() {
        CompleteRegistrationButton.click();
        RegistrationFinishedAlert = switchTo().alert();
        assertTrue(RegistrationFinishedAlert.getText().contains("Бронирование завершено"));
        RegistrationFinishedAlert.accept();
    }
    public void finish_registration_wrong_passport_number() {
        CompleteRegistrationButton.click();
        RegistrationMessage.shouldHave(text("Номер паспорта должен содержать только цифры и пробелы."));
    }
    public void fill_form(String passengerName, String passportNumber, String email, String phone) {
        if (passengerName != null) PassengerName.setValue(passengerName);
        if (passportNumber != null) PassportNumber.setValue(passportNumber);
        if (email != null) Email.setValue(email); if (phone != null)  Phone.setValue(phone);
    }
    public void verify_autofilled_data() {
        //Проверяем, что поля заполнились автоматически
        PassengerName.shouldNotBe(empty);
        PassportNumber.shouldNotBe(empty);
        Email.shouldNotBe(empty);
        Phone.shouldNotBe(empty);
        //Проверяем значения в полях
        PassengerName.shouldHave(value("Иванов Иван Иванович"));
        Email.shouldHave(value("ivanov@example.com"));
        PassportNumber.shouldHave(value("1234 567890"));
        Phone.shouldHave(value("+7 (123) 456-7890"));
    }
    public void clear_form() {
        PassengerName.clear();
        PassportNumber.clear();
        Email.clear();
        Phone.clear();
        CompleteRegistrationButton.click();
        RegistrationMessage.shouldHave(text("Пожалуйста, заполните все поля."));
    }
}