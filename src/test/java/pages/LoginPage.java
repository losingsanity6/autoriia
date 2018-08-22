package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;
import utils.Utils;

import static utils.DriverProvider.driver;

import java.util.ArrayList;

public class LoginPage {
    private final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LoginPage.class);
    private final By numberInput = By.id("emailloginform-email");
    private final By passwordInput = By.id("emailloginform-password");
    private final By LoginBtn = By.xpath("//*[@id='login-form']/div[@class='login-link']/button");
    private final By FacebookLocator = By.linkText("Войти через Facebook");
    private final By noProfileMessage = By.xpath("/html/body/div/div[@class='change-password']");
    private final By facebookEmail = By.id("email");
    private final By facebookPassword = By.id("pass");
    private final By facebookLoginButton = By.id("loginbutton");
    private final By errorLogin = By.xpath("//form[@id='login-form']/div/p[@class=error login-link]");
    private final String loginFrame = "login_frame";


    public LoginPage LoginInput(String password, String number) {
        driver.findElement(numberInput).sendKeys(number);
        log.info("Data to e-mail field was inputted");
        driver.findElement(passwordInput).sendKeys(password);
        log.info("Data to password field was inputted");
        return this;
    }


    public LoginPage loginViaFacebook() {
        Utils utils = new Utils();
        utils.ScrollWindow(FacebookLocator);
        driver.findElement(FacebookLocator).click();
        log.info("Click on facebook login button was perfomed");
        return this;

    }


    public LoginPage switchBetweenFrame() {
        driver.switchTo().frame(loginFrame);
        log.info("Switched to login frame");
        return this;
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
        Utils utils = new Utils();
        utils.switchBetweenWindows(1);
        driver.findElement(facebookEmail).sendKeys(email);
        log.info("Data to e-mail field was inputted");
        driver.findElement(facebookPassword).sendKeys(pass);
        log.info("Data to password field was inputted");
        driver.findElement(facebookLoginButton).click();
        log.info("Click on Facebook login button was perfomed");
        utils.switchBetweenWindows(0);

    }

}