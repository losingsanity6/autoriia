import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestSuit {
    private WebDriver driver;
    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "H:\\Testing\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://auto.ria.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

    }

   /* @After
    public void close() {
        driver.close();
    }*/

    @Test
    public void selectCarNew() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.selectCarNew();
        mainPageObject.clickSearchButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        String carName = resultPageObject.dropDownCar();
        System.out.println(carName);
        Assert.assertEquals("Aston Martin", carName);
    }

    @Test
    public void priceFieldTestFromLowestToHighest() {
        String priceFrom = Integer.toString(1);
        String priceTo = Integer.toString(1000000000);
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.priceFieldInput(priceFrom, priceTo);
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        resultPageObject.ScrollWindow();
        System.out.println(""+ resultPageObject.getTextFromPriceInputFrom()+ resultPageObject.getInputFromPriceFieldTo());
        Assert.assertEquals("1", resultPageObject.getTextFromPriceInputFrom());
        //   String h1 = resultPageObject.getH1Text();

    }


    @Test
    public void InvalidData() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.priceFieldInput("gggggggg","wqwqwqw");
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        Assert.assertEquals("", resultPageObject.getTextFromPriceInputFrom());

    }

    @Test
    public void UsedCarsFilterTest() {
        String yearFrom = Integer.toString(2000);
        String yearTo = Integer.toString(2018);
        String priceTo = Integer.toString(19000);
      MainPageObject mainPageObject = new MainPageObject(driver);
      mainPageObject.carBrandDropdownUsedInput("Daewoo");
      mainPageObject.clickCarInAutocompletedSearch();
      driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
      mainPageObject.Model("Korando");
      mainPageObject.clickModelInAutocompletedSearch();
      mainPageObject.clickRegion("Киев");
      mainPageObject.clickAutocompletedRegion();
      mainPageObject.selectYearFrom(yearFrom, yearTo);
      mainPageObject.priceFieldInput("",priceTo);
        mainPageObject.clickSearchButton();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        Assert.assertTrue(resultPageObject.noResultsMessage().contains("Объявлений не найдено"));

    }
@Test
public void validLoginTest(){
        driver.get("https://auto.ria.com/login.html");
driver.switchTo().frame("login_frame");
LoginPageObject loginPageObject = new LoginPageObject(driver);
loginPageObject.LoginInput("iamonria", "380637017113");
loginPageObject.clickLoginBtn();

}
@Test
public void InvalidLoginTest(){
        driver.get("https://auto.ria.com/login.html");
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.LoginInput("invalidPassword", "thisisinvalidnumber");
        loginPageObject.clickLoginBtn();

}

    @Test
    public void loginViaFacebook(){
        driver.get("https://auto.ria.com/login.html");
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.loginViaFacebook();
       // Set<String> handle= driver.getWindowHandles();
        Set handles = driver.getWindowHandles();

        driver.switchTo().window("Facebook");
        driver.findElement(By.id("email")).sendKeys("12234");

       // driver.switchTo().window();



    }
    @Test
    public void checkBoxesSedanExtendedSearch() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.ExtendedSearchButtonClick();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        ExtendedSearchPageObject extendedSearchPageObject = new ExtendedSearchPageObject(driver);
        extendedSearchPageObject.Checkbox();
        extendedSearchPageObject.clickShowbutton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        System.out.println(resultPageObject.getH1Text());
        Assert.assertTrue(resultPageObject.getH1Text().contains("Седан"));
    }



}
