package tests;

import org.apache.log4j.Logger;
import pages.LoggedInPage;
import pages.LoginPage;
import pages.MainPage;
import sun.rmi.runtime.Log;
import utils.Annotations;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginTest extends Annotations {
    private final Logger log = Logger.getLogger(LoginTest.class);


    @Test(dataProvider = "Login", dataProviderClass = data_provider.Data_Provider.class)
    public void invalidLoginTest(String login, String password, String message) {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.switchBetweenFrame()
                .LoginInput(password, login)
                .clickLoginButton();
        try {
            Assert.assertTrue(loginPage.invalidPhoneMessage().contains(message), "The message does not contain that text");
        } catch (Exception e) {
            log.error("Captcha appeared");
            throw e;
        }

    }

    @Test(dataProvider = "FacebookLogin", dataProviderClass = data_provider.Data_Provider.class)
    public void loginViaFacebook(String login, String password, String message) {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        loginPage.switchBetweenFrame()
                .loginViaFacebook();
        loginPage.LoginFacebook(login, password);
        loginPage.switchBetweenFrame();
        Assert.assertTrue(loginPage.Message().contains(message), " The message does not contain text");

    }


}
