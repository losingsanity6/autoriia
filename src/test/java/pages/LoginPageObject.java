package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {

    private WebDriver driver;
    private By numberInput = By.id("emailloginform-email");
    private By passwordInput = By.id("emailloginform-password");
    private By LoginBtn = By.xpath("//*[@id=\"login-form\"]/div[4]/button");
    private By FacebookLocator = By.linkText("Войти через Facebook");
    private By noProfileMessage = By.xpath("/html/body/div/div[1]");
    private By facebookEmail = By.id("email");
    private By facebookPassword = By.id("pass");
    private By facebookLoginButton = By.id("loginbutton");
    private By errorLogin = By.xpath("//*[@id=\"login-form\"]/div[1]/p");

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void LoginInput(String password, String number) {
        driver.findElement(numberInput).sendKeys(number);
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void loginViaFacebook() {
        driver.findElement(FacebookLocator).click();

    }

    public void switchBetweenFrame() {
        driver.switchTo().frame("login_frame");
    }

    public void clickLoginButton() {
        driver.findElement(LoginBtn).click();
    }


    public String Message() {
        return driver.findElement(noProfileMessage).getAttribute("innerHTML");
    }

    public String invalidPhoneMessage() {
        return driver.findElement(errorLogin).getAttribute("innerHTML");
    }

    public void LoginFacebook(String email, String pass) {
        driver.findElement(facebookEmail).sendKeys(email);
        driver.findElement(facebookPassword).sendKeys(pass);
        driver.findElement(facebookLoginButton).click();
    }
}