package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class FlightsFoundList {
    SelenideElement
            firstRegButton = $x("//button[@class='register-btn']"),
            flightsCount = $x("//div[@id='flightsCount']");

//    ElementsCollection
//        flightsList = $$x("//table[@id='flightsContainer']/tbody/tr/td");

    public void chooseFirstFlight() {
        firstRegButton.click();
    }

    public void verifySuccessfullSearch() {
        flightsCount.shouldNotHave(text("Найдено рейсов: 0"));
        //assertEquals(7, flightsList.size());
        firstRegButton.shouldBe(visible);
    }
}
