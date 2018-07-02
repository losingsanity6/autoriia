package tests;



import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExtendedSearchPage;
import pages.MainPage;
import pages.NewCarsPage;
import pages.ResultPage;
import utils.Annotations;


public class FiltersTest extends Annotations {

private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FiltersTest.class);

    @Test
    public void checkWorkOfNewCarsFilter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.selectCarNew();
        log.error("The element was not found");
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        String carName = resultPage.findCarDropdown();
        System.out.println(carName);
        Assert.assertEquals("BMW", carName, "The car dropdown was not found");

    }

    @Test //TODO: add params
    public void usedCarsFilterTest() {
        String yearFrom = "2000"; //TODO: move to annotation Test
        String yearTo = "2018";
        String priceTo = "19000";
        MainPage mainPage = new MainPage(driver);
        mainPage.chooseCarBrand("Daewoo");
        mainPage.clickModel("Korando");
        mainPage.clickRegion("Киев");
        mainPage.selectYearFrom(yearFrom, yearTo);
        mainPage.enterPriceToPriceField("", priceTo);
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertTrue(resultPage.getTextFromNoResultsMessage().contains("Объявлений не найдено"), "The message does not contain the text");//TODO:add errors
        //TODO: add logs

    }

    @Test
    public void extendedSearchCheckBoxes() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickExtendedSearchButton();
        ExtendedSearchPage extendedSearchPage = new ExtendedSearchPage(driver);
        extendedSearchPage.clickCheckBoxes();
        extendedSearchPage.clickShowButton();
        ResultPage resultPage = new ResultPage(driver);
        System.out.println(resultPage.getH1Text());
        Assert.assertTrue(resultPage.getH1Text().contains("Седан"), "The header contains other text");
    }


    @Test
    public void priceFieldTestFromLowestToHighest() {
        String priceFrom = "1";
        String priceTo = "100000000000";
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPriceToPriceField(priceFrom, priceTo);
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        System.out.println("" + resultPage.getTextFromPriceInputFrom() + resultPage.getInputFromPriceFieldTo());
        Assert.assertEquals("1", resultPage.getTextFromPriceInputFrom(), "The price field does not contain parameter");


    }


    @Test
    public void invalidDataForPriceField() {
        MainPage mainPage = new MainPage(driver);
        mainPage.enterPriceToPriceField("gggggggg", "wqwqwqw");
        mainPage.clickSearchButton();
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertEquals("", resultPage.getTextFromPriceInputFrom(), "The price field does not contain the parameter");

    }

    @Test
    public void checkPages() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickNewCars();
        NewCarsPage newCarsPage = new NewCarsPage(driver);
        String VolkswagenUrl = newCarsPage.clickCar();
        String golfUrl = newCarsPage.clickGolf();
        String chetchbackUrl = newCarsPage.clickFirstImage();
        Assert.assertTrue(VolkswagenUrl.contains("volkswagen") && golfUrl.contains("golf") && chetchbackUrl.contains("khetchbek"), "The urls don't contain defined words ");


    }

   /* @Test
    public void detailsAuto() {
        MainPage mainPageObject = new MainPage(driver);
        mainPageObject.allForCarsDropDown();
        AllForAutoPage allForAutoPage = new AllForAutoPage(driver);
        allForAutoPage.clickDetailsForCars();
        allForAutoPage.selectCarBrand();
        allForAutoPage.selectCarModel();
        allForAutoPage.clickSearchButton();

    }
*/
}

