import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class MainPageObject {
    private WebDriver driver;

    private By usedCarDropdown = By.id("brandTooltipBrandAutocomplete-brand");
    private By newCarDropdown = By.id("marks");
    private By searchButton = By.xpath("//*[@id=\"mainSearchForm\"]/div[3]/button");
    private By priceFieldFrom = By.id("priceFrom");
    private By priceFieldTo = By.id("priceTo");
    private By extendedSeachButton = By.xpath("//*[@id=\"mainSearchForm\"]/div[3]/a");
    private By newCarsRadioBtn = By.xpath("//*[@id=\"mainSearchForm\"]/div[1]/label[3]");
    private final int PRICE_FROM = 1;
    private final int PRICE_TO = 100000000;

    public MainPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void ExtendedSechButtonClick() {
        driver.findElement(extendedSeachButton).click();
    }

    public void carBrandDropdownClick() {
        driver.findElement(usedCarDropdown).click();

    }
    // public void Selectcar(){

    //   }
    public void selectCarNew() {
        driver.findElement(newCarsRadioBtn).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement MarkCarDrpdwn = driver.findElement(By.name("marka_id"));
        Select selectMarkCar = new Select(MarkCarDrpdwn);
        selectMarkCar.selectByIndex(4);

    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }


    public void PriceField() {

        driver.findElement(priceFieldFrom).sendKeys("" + PRICE_FROM);
        driver.findElement(priceFieldTo).sendKeys("" + PRICE_TO);
    }
}
