package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
    public void openingPage() {
        open("http://localhost:9999");
    }


    @Test
    public void shouldCardDelivery() throws InterruptedException {

        final InputFields validUser = DataGeneration.Registration.generateByCard("ru");
        String dateOfTheMeeting = DataGeneration.generateDate(4);
        String dateOfTheMeeting2 = DataGeneration.generateDate(10);
        //первая фаза теста
        $("[data-test-id='city'] .input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateOfTheMeeting);
        $(byName("name")).setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byClassName("button")).click();
        $(byText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateOfTheMeeting), Duration.ofSeconds(15));
        //вторая фаза теста
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateOfTheMeeting2);
        $(byClassName("button")).click();
        $(byText("Перепланировать")).shouldBe(Condition.appear, Duration.ofSeconds(15)).click();
        $(byText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + dateOfTheMeeting2), Duration.ofSeconds(15));

    }


}
