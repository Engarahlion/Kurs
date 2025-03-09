package ru.netology.card.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.card.data.DataHelper;
import ru.netology.card.data.SQLHelper;
import ru.netology.card.page.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    void successPaymentApprovedCard() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getApprovedCardInfo();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.successNotification();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatus());
    }

    @Test
    void successPaymentDeclinedCard() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.successNotification();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatus());
    }

    @Test
    void paymentMonthZeroOne() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getMonthZeroOne();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.successNotification();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatus());
    }

    @Test
    void paymentMonthTwelve() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getMonthTwelve();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.successNotification();
        Assertions.assertEquals("APPROVED", SQLHelper.getStatus());
    }

    @Test
    void paymentWrongFormatCard() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getWrongFormatCardInfo();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверный формат");
    }

    @Test
    void paymentWrongCard() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getWrongCardInfo();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.failNotification();
        Assertions.assertEquals("DECLINED", SQLHelper.getStatus());
    }

    @Test
    void paymentWrongFormatMonth() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getWrongFormatMonth();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверный формат");
    }

    @Test
    void paymentMonthZero() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getMonthZero();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверный формат");
    }

    @Test
    void paymentMonth13() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getMonth13();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверно указан срок действия карты");
    }

    @Test
    void paymentExpiredCard() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getExpiredCardInfo();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Истёк срок действия карты");
    }

    @Test
    void paymentOldCardBackMonth() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.getExpiredCardInfoBackMonth();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверно указан срок действия карты");
    }

    @Test
    void setNameRu() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.nameRu();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверный формат");
    }

    @Test
    void setNameNumbers() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.nameNumbers();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверный формат");
    }

    @Test
    void setNameSymbols() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.nameSymbols();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Неверный формат");
    }

    @Test
    void setNameSpace() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.nameSpace();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorNotification("Поле обязательно для заполнения");
    }

    @Test
    void setSpaceField() {
        var mainPage = new MainPage();
        var paymentPage = mainPage.openPaymentPage();
        var cardInfo = DataHelper.spaceField();
        paymentPage.enterCardInfo(cardInfo);
        paymentPage.errorAllNotification();
    }
}