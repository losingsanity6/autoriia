package tests;

import org.apache.log4j.Logger;
import pages.LoggedInPage;
import pages.LoginPage;
import pages.MainPage;
import utils.Annotations;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginTest extends Annotations {
    private final Logger log = Logger.getLogger(LoginTest.class);


    @Test
    public void invalidLoginTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        driver.switchTo().frame("login_frame");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.LoginInput("invalidPassword", "948594835-03-04930459430589458");
        loginPage.clickLoginButton();
        try {
            Assert.assertTrue(loginPage.invalidPhoneMessage().contains("неверный мобильный номер телефона"), "The message does not contain that text");
        } catch (Exception e) {
           log.error("Captcha appeared");
           throw  e;
        }

    }

    @Test
    public void loginViaFacebook() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.switchBetweenFrame();
        loginPage.loginViaFacebook();
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        loginPage.LoginFacebook("tanyalondon1@mail.ru", "donotusethispassword");
        driver.switchTo().window(windowHandles.get(0));
        loginPage.switchBetweenFrame();
        Assert.assertTrue(loginPage.Message().contains("Не удалось"), " The message does not contain text");

    }


}
