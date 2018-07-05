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
    private final By LoginBtn = By.xpath("//*[@id=\"login-form\"]/div[4]/button");
    private final By FacebookLocator = By.linkText("Войти через Facebook");
    private final By noProfileMessage = By.xpath("/html/body/div/div[1]");
    private final By facebookEmail = By.id("email");
    private final By facebookPassword = By.id("pass");
    private final By facebookLoginButton = By.id("loginbutton");
    private final By errorLogin = By.xpath("//*[@id=\"login-form\"]/div[1]/p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void LoginInput(String password, String number) {
        driver.findElement(numberInput).sendKeys(number);
        log.info("Data to e-mail field was inputted");
        driver.findElement(passwordInput).sendKeys(password);
        log.info("Data to password field was inputted");
    }

    @Step
    public void loginViaFacebook() {
        driver.findElement(FacebookLocator).click();
        log.info("Click on facebook login button was perfomed");

    }

    @Step
    public void switchBetweenFrame() {
        driver.switchTo().frame("login_frame");
        log.info("Switched to login frame");
    }

    @Step
    public void clickLoginButton() {
        driver.findElement(LoginBtn).click();
        log.info("Click on login button was perfomed");

    }

    @Step
    public String Message() {
        return driver.findElement(noProfileMessage).getAttribute("innerHTML");

    }

    @Step
    public String invalidPhoneMessage() {
        return driver.findElement(errorLogin).getAttribute("innerHTML");
    }

    @Step
    public void LoginFacebook(String email, String pass) {
        driver.findElement(facebookEmail).sendKeys(email);
        log.info("Data to e-mail field was inputted");
        driver.findElement(facebookPassword).sendKeys(pass);
        log.info("Data to password field was inputted");
        driver.findElement(facebookLoginButton).click();
        log.info("Click on Facebook login button was perfomed");
    }
}