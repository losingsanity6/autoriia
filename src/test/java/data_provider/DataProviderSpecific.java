package data_provider;

import org.testng.annotations.DataProvider;

public class DataProviderSpecific {
    @DataProvider(name = "NewCarsFilters")
    public static Object[][] usedFiters() {
        return new Object[][]{
                {"Volkswagen", "Beetle", "Винница", "2010", "2018", "1", "10 0000", "К сожалению мы не смогли найти предложений подходящих Вашему запросу"},
                {"Audi", "A2", "Винница", "2010", "2018", "", "9999999999", "К сожалению мы не смогли найти предложений подходящих Вашему запросу"},

        };
    }

    @org.testng.annotations.DataProvider(name = "TesForUsedFilters")
    public static Object[][] newCars() {
        return new Object[][]{
                {"Daewoo", "Musso", "Киев", "2000", "2007", "1", "10 0000", "Объявлений не найдено"},
                {"Audi", "A2", "Винница", "1985", "2005", "", "9999999999", "Объявлений не найдено"},

        };
    }

    @org.testng.annotations.DataProvider(name = "invalidDataForPriceField")
    public static Object[][] invalidPrice() {
        return new Object[][]{
                {"awerrtre", "text", ""},
                {"******", "********", ""},
                {"@!`", "@!`)", ""},
                {"log2(8) = 3", "ln 1.3", ""},
                {"e0.262364", "e0.262364", ""},
                {" 100.8704", "100.9878677", ""},
                {"&?&?", "&?&?", ""},
                {"----", "------", ""},
                {"%100", "$100", ""},
        };
    }

    @org.testng.annotations.DataProvider(name = "boundariesForPriceField")
    public static Object[][] boundarieForPriceField() {
        return new Object[][]{
                {"1", "1", "1"},
                {"1000000000000000", "100000000000000", "1000000000000000"},
                {"5555555555", "55555555555", "5555555555"},
                {"0,01", "0,01", "1"},
                {"1000000000000000000001", "10000000000000000001", "1000000000000000000001"},

        };
    }

    @org.testng.annotations.DataProvider(name = "Login")
    public static Object[][] loginInvalidUser() {
        return new Object[][]{{"invalidLogin", "invalidPassword", "неверный мобильный номер телефона"},
                {"09320930293091-3901-", "ksjfkjdskf", "неверный мобильный номер телефона"},

        };
    }

    @DataProvider(name = "FacebookLogin")
    public static Object[][] facebookLogin() {
        return new Object[][]{
                {"tanyalondon1@mail.ru", "donotusethispassword", "Не удалось"},
        };
    }

    @DataProvider(name = "linkNames")
    public static Object[][] linkNames() {
        return new Object[][]{
                {"Новые авто", "Volkswagen", "Golf"}
        };
    }

    @DataProvider(name = "checkboxes")
    public static Object[][] checkBox() {
        return new Object[][]{
                {"Седан", "Англия"},
                {"Универсал", "Англия"}
        };
    }

    @org.testng.annotations.DataProvider(name = "Detailsforautopage")
    public static Object[][] detailsForAuto() {
        return new Object[][]{
                {"Каталог авторазборок"}
        };
    }
}

