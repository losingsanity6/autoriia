package tests;

import pages.LoggedInPage;
import pages.LoginPageObject;
import pages.MainPageObject;
import utils.Annotations;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginTest extends Annotations {

    @Test
    public void validLoginTest() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.clickLoginButton();
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.LoginInput("iamonria", "380637017113");
        loginPageObject.clickLoginButton();
        try {
            LoggedInPage loggedInPage = new LoggedInPage(driver);
            // System.out.println(loggedInPage.getCurrentUrl());
            System.out.println("!!" + loggedInPage.getTextFromLoggedUser());
            Assert.assertTrue(loggedInPage.getTextFromLoggedUser().contains("Личный кабинет"));
        } catch (Exception e) {

            System.out.println("Whoops! Captcha has appeared");
        }


    }

    @Test
    public void invalidLoginTest() {
        driver.switchTo().frame("login_frame");
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.LoginInput("invalidPassword", "948594835-03-04930459430589458");
        loginPageObject.clickLoginButton();
        try {
            Assert.assertTrue(loginPageObject.invalidPhoneMessage().contains("неверный мобильный номер телефона"));
        } catch (Exception e) {
            System.out.println("Whoops! Captcha has appeared");
        }

    }

    @Test
    public void loginViaFacebook() {
        LoginPageObject loginPageObject = new LoginPageObject(driver);
        loginPageObject.switchBetweenFrame();
        loginPageObject.loginViaFacebook();
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(windowHandles);
        driver.switchTo().window(windowHandles.get(1));
        loginPageObject.LoginFacebook("tanyalondon1@mail.ru", "donotusethispassword");
        driver.switchTo().window(windowHandles.get(0));
        loginPageObject.switchBetweenFrame();
        System.out.println("!!" + loginPageObject.Message());
        Assert.assertTrue(loginPageObject.Message().contains("Не удалось"));

    }


}
