package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class FlightsLoginPage {
    SelenideElement Username, Password, LoginButton, Message;
    String successMessage, errorMessage,emptyMessage,blockingMessage;
    public FlightsLoginPage() {
        Username = $("#username");
        Password = $("#password");
        LoginButton = $("#loginButton");
        Message = $("#message");

        successMessage = "Вход в систему выполнен успешно! Загрузка...";
        errorMessage = "Неверное имя пользователя или пароль.";
        emptyMessage = "Username and Password are required.";
        blockingMessage = "Пользователь заблокирован.";
    }

    public void login(String username, String password) {
        Username.setValue(username);
        Password.setValue(password);
        LoginButton.click();
    }

    public void verify_successful_login() {
        Message.shouldHave(text(successMessage));
    }

    public void verify_wrong_username_or_password() {
        Message.shouldHave(text(errorMessage));
    }
    public void verify_wrong_empty() {
        Message.shouldHave(text(emptyMessage));
    }
    public void verify_wrong_blocking() {
        Message.shouldHave(text(blockingMessage));
    }
}

