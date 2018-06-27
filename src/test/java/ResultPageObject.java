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
    private By priceInput = By.name("price.USD.gte");

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
    public String getTextFromPriceField(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(priceInput));
         return driver.findElement(priceInput).getAttribute("value");



    }
}