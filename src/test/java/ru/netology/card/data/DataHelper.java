package ru.netology.card.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));

    @Value
    public static class CardInfo {
        private String card;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo("1111 2222 3333 4444", generateMonth(1), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("5555 6666 7777 8888", generateMonth(1), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getWrongCardInfo() {
        return new CardInfo(FAKER.numerify("#### #### #### ####"), generateMonth(1), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getWrongFormatCardInfo() {
        return new CardInfo(FAKER.numerify("#### #### #### ###"), generateMonth(1), generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    private static String generateMonth(int month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String generateRandomYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String generateExpiredYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String generateNowYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String generateRandomCVC() {
        return FAKER.numerify("###");
    }

    private static String generateRandomName() {
        return FAKER.name().fullName();
    }

    public static CardInfo getMonthZeroOne() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "01", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getMonthTwelve() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "12", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getMonthZero() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "0", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getWrongFormatMonth() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "1", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getMonth13() {
        return new CardInfo(FAKER.numerify("1111 2222 3333 4444"), "13", generateRandomYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getExpiredCardInfo() {
        return new CardInfo("1111 2222 3333 4444", generateMonth(-1), generateExpiredYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo getExpiredCardInfoBackMonth() {
        return new CardInfo("1111 2222 3333 4444", generateMonth(-1), generateNowYear(), generateRandomName(), generateRandomCVC());
    }

    public static CardInfo nameRu() {
        return new CardInfo("1111 2222 3333 4444", generateMonth(1), generateRandomYear(), "Иван Иванов", generateRandomCVC());
    }

    public static CardInfo nameNumbers() {
        return new CardInfo("1111 2222 3333 4444", generateMonth(1), generateRandomYear(), "123123123", generateRandomCVC());
    }

    public static CardInfo nameSymbols() {
        return new CardInfo("1111 2222 3333 4444", generateMonth(1), generateRandomYear(), "!\"№;%:", generateRandomCVC());
    }

    public static CardInfo nameSpace() {
        return new CardInfo("1111 2222 3333 4444", generateMonth(1), generateRandomYear(), "          ", generateRandomCVC());
    }

    public static CardInfo spaceField() {
        return new CardInfo("", "", "", "", "");
    }
}