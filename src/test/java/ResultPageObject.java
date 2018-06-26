import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPageObject {
    private WebDriver driver;
    private By inputPtice = By.name("price.USD.lte");
    public ResultPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public String getTitle() {
        return driver.getTitle();
    }
}
