import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TestSuit {
    private WebDriver driver;
    String priceFro = Integer.toString(10);
    String priceToo = Integer.toString(1000000);

    @Before
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://auto.ria.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

    }

   /* @After
    public void close() {
        driver.close();
    }*/

    //@Test
  /*  public void carFilterTest() {
        String metaname = "BMW";
        MainPageObject mainPageObject = new MainPageObject(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPageObject.Selectcar();
        mainPageObject.clickSearchButton();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ResultPageObject resultPageObject = new ResultPageObject(driver);
       // Assert.assertTrue(resultPageObject.getTitle().toLowerCase().contains(metaname));


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
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.PriceField();
        mainPageObject.clickSearchButton();
        ResultPageObject resultPageObject = new ResultPageObject(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("!!1 " + resultPageObject.getTextFromPriceField());
        //   String h1 = resultPageObject.getH1Text();

    }

    @Test
    public void PriceField() {

        WebElement priceFrom = driver.findElement(By.id("priceFrom"));
        WebElement priceTo = driver.findElement(By.id("priceTo"));
        priceFrom.sendKeys(priceFro);
        priceTo.sendKeys(priceToo);

    }

    @Test
    public void InvalidData() {
        final String textPrice = "gjjgjg";
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.PriceField();
        mainPageObject.clickSearchButton();


    }

    @Test
    public void DropdownSearch() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.carBrandDropdownClick();
        WebElement search = driver.findElement(By.id("brandTooltipBrandAutocompleteInput-brand"));
        search.sendKeys("Daewoo");
        search.sendKeys(Keys.RETURN);
        mainPageObject.clickSearchButton();
    }

    @Test
    public void checkBoxes() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.ExtendedSechButtonClick();
        ExtendedSearchPageObject extendedSearchPageObject = new ExtendedSearchPageObject(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        extendedSearchPageObject.Checkbox();
        extendedSearchPageObject.clickShowbutton();
    }

    @Test
    public void CheckCarLocator() {
        WebElement cardropdown = driver.findElement(By.id("brandTooltipBrandAutocomplete-brand"));
        cardropdown.click();
        Select carFromDropdown = new Select(driver.findElement(By.xpath("//*[@id=\"brandTooltipBrandAutocomplete-brand\"]/ul")));
        carFromDropdown.selectByVisibleText("BMW");

    }

}
