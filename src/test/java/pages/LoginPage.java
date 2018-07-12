package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

public class LoginPage {
    private final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginPage.class);
    private final WebDriver driver;
    private final By numberInput = By.id("emailloginform-email");
    private final By passwordInput = By.id("emailloginform-password");
    private final By LoginBtn = By.xpath("//*[@id='login-form']/div[@class='login-link']/button");
    private final By FacebookLocator = By.linkText("Войти через Facebook");
    private final By noProfileMessage = By.xpath("/html/body/div/div[@class='change-password']");
    private final By facebookEmail = By.id("email");
    private final By facebookPassword = By.id("pass");
    private final By facebookLoginButton = By.id("loginbutton");
    private final By errorLogin = By.xpath("//*[@id='login-form']/div[1]/p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void LoginInput(String password, String number) {
        driver.findElement(numberInput).sendKeys(number);
        log.info("Data to e-mail field was inputted");
        driver.findElement(passwordInput).sendKeys(password);
        log.info("Data to password field was inputted");
    }


    public void loginViaFacebook() {
        driver.findElement(FacebookLocator).click();
        log.info("Click on facebook login button was perfomed");

    }


    public void switchBetweenFrame() {
        driver.switchTo().frame("login_frame");
        log.info("Switched to login frame");
    }


    public void clickLoginButton() {
        driver.findElement(LoginBtn).click();
        log.info("Click on login button was perfomed");

    }


    public String Message() {
        return driver.findElement(noProfileMessage).getAttribute("innerHTML");

    }


    public String invalidPhoneMessage() {
        return driver.findElement(errorLogin).getAttribute("innerHTML");
    }


    public void LoginFacebook(String email, String pass) {
        driver.findElement(facebookEmail).sendKeys(email);
        log.info("Data to e-mail field was inputted");
        driver.findElement(facebookPassword).sendKeys(pass);
        log.info("Data to password field was inputted");
        driver.findElement(facebookLoginButton).click();
        log.info("Click on Facebook login button was perfomed");
    }
}