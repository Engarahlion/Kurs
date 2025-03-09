package ru.netology.card.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement payButton = $(byText("Купить"));

    public PaymentPage openPaymentPage() {
        payButton.click();
        return new PaymentPage();
    }
}