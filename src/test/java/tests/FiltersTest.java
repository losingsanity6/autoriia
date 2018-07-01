package tests;


import pages.*;
import utils.Annotations;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FiltersTest extends Annotations {


    @Test
    public void checkWorkOfNewCarsFilter() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.selectCarNew();
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        String carName = resultPageObject.findCarDropdown();
        System.out.println(carName);
        Assert.assertEquals("BMW", carName);
    }

    @Test
    public void UsedCarsFilterTest() {
        String yearFrom = "2000";
        String yearTo = "2018";
        String priceTo = "19000";
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.chooseCarBrand("Daewoo");
        mainPageObject.clickModel("Korando");
        mainPageObject.clickRegion("Киев");
        mainPageObject.selectYearFrom(yearFrom, yearTo);
        mainPageObject.enterPriceToPriceField("", priceTo);
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        Assert.assertTrue(resultPageObject.getTextFromNoResultsMessage().contains("Объявлений не найдено"));

    }

    @Test
    public void extendedSearchCheckBoxes() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.clickExtendedSearchButton();
        ExtendedSearchPageObject extendedSearchPageObject = new ExtendedSearchPageObject(driver);
        extendedSearchPageObject.clickCheckBoxes();
        extendedSearchPageObject.clickShowButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        System.out.println(resultPageObject.getH1Text());
        Assert.assertTrue(resultPageObject.getH1Text().contains("Седан"));
    }


    @Test
    public void priceFieldTestFromLowestToHighest() {
        String priceFrom = "1";
        String priceTo = "100000000000";
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.enterPriceToPriceField(priceFrom, priceTo);
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        System.out.println("" + resultPageObject.getTextFromPriceInputFrom() + resultPageObject.getInputFromPriceFieldTo());
        Assert.assertEquals("1", resultPageObject.getTextFromPriceInputFrom());


    }


    @Test
    public void InvalidDataForPriceField() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.enterPriceToPriceField("gggggggg", "wqwqwqw");
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        Assert.assertEquals("", resultPageObject.getTextFromPriceInputFrom());

    }

    @Test
    public void checkPages() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.clickNewCars();
        NewCarsPage newCarsPage = new NewCarsPage(driver);
        String VolkswagenUrl = newCarsPage.clickCar();
        String golfUrl = newCarsPage.clickGolf();
        String chetchbackUrl = newCarsPage.clickFirstImage();
        Assert.assertTrue(VolkswagenUrl.contains("volkswagen") && golfUrl.contains("golf") && chetchbackUrl.contains("khetchbek"));


    }

    @Test
    public void detailsAuto() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.allForCarsDropDown();
        AllForAutoPage allForAutoPage = new AllForAutoPage(driver);
        allForAutoPage.clickDetailsForCars();
        allForAutoPage.selectCarBrand();
        allForAutoPage.selectCarModel();
        allForAutoPage.clickSearchButton();

    }

}

