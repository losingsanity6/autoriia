package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInPage {
    private WebDriver driver;
    private By loggedInUserLocator = By.xpath("//*[@id=\"headerLinkToMyMenu\"]");
    public LoggedInPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextFromLoggedUser(){
       return driver.findElement(loggedInUserLocator).getAttribute("innerHTML");
    }
   // public String getCurrentUrl(){return getCurrentUrl(); }
}
