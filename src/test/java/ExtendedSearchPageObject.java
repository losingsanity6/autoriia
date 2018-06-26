import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExtendedSearchPageObject {
    private WebDriver driver;

    private By checkboxSedan = By.xpath("//*[@id=\"bodies\"]/ul/li[1]");
    private By checkboxEngland = By.name("Англия");
    private  By showButton = By.xpath("//*[@id=\"results\"]/div/a[1]");

    public ExtendedSearchPageObject(WebDriver driver) {
        this.driver = driver;
    }
        public void clickShowbutton () {
            driver.findElement(showButton).click();
        }
        public void Checkbox(){
        driver.findElement(checkboxSedan).click();
        driver.findElement(checkboxEngland).click();
        }
    }