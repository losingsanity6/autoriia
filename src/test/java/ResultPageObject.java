import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPageObject {
    private WebDriver driver;
    private By inputPtice = By.name("price.USD.lte");
    private By h1 = By.id("h1LabelsAfter");
    private By dropdownCar = By.xpath("//*[@id=\"autoCmplt\"]/label");
    private By priceInputFrom = By.name("price.USD.gte");
    private By priceInputTo = By.name("price.USD.lte");
    private By noAdsMessage = By.id("emptyResultsNotFoundBlock");

    public ResultPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getH1Text() {
        return driver.findElement(h1).getText();


    }

    public String dropDownCar() {
        return driver.findElement(dropdownCar).getAttribute("data-text");

    }
    public void ScrollWindow(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(priceInputFrom));
    }
    public String getTextFromPriceInputFrom(){

     return driver.findElement(priceInputFrom).getAttribute("value");


    }
    public String getInputFromPriceFieldTo(){
        return driver.findElement(priceInputTo).getAttribute("value");
    }
    public String noResultsMessage(){
        return driver.findElement(noAdsMessage).getText();
    }
}