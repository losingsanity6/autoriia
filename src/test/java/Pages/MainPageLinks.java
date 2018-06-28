package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageLinks {
    private WebDriver driver;
    private By NewCarsLink = By.linkText("Новые авто");
    public MainPageLinks(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNewCars(){
        driver.findElement(NewCarsLink).click();

    }

}
