package Tests_LoginFunctionality;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginFunctionality extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginFunctionality.class);

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");

    }


    @Test
    public void userCanLoginWithCorrectUsernameAndPassword(){
        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);

        logIn(username, password);
        inventoryPage.clickOnHambuergerManu();

        wait.until(ExpectedConditions.visibilityOf(burgerMenuPage.logoutButton));
        Assert.assertTrue(burgerMenuPage.logoutButton.isDisplayed());
        Assert.assertEquals(inventoryPage.getHeaderText(), "Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        
    }

    @Test
    public void userCannotLoginWithInvalidUsernameAndValidPassword(){

        String invalidUsername = excelReader.getStringData("UsersCredentials", 1, 2);
        String password = excelReader.getStringData("UsersCredentials", 1, 3);

        loginPage.inputUsername(invalidUsername);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithValidUsernameAndInvalidPassword(){

        String username = excelReader.getStringData("UsersCredentials", 2, 2);
        String invalidPassword = excelReader.getStringData("UsersCredentials", 2, 3);

        loginPage.inputUsername(username);
        loginPage.inputPassword(invalidPassword);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithInvalidUsernameAndInvalidPassword(){

        String username = excelReader.getStringData("UsersCredentials", 3, 2);
        String invalidPassword = excelReader.getStringData("UsersCredentials", 3, 3);

        loginPage.inputUsername(username);
        loginPage.inputPassword(invalidPassword);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithBlankUsernameAndAnyPassword(){

        String blankUsername = "";
        String password = excelReader.getStringData("UsersCredentials", 1, 1);

        loginPage.inputUsername(blankUsername);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username is required");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithAnyUsernameAndBlankPassword(){

        String username = excelReader.getStringData("UsersCredentials", 3, 2);
        String blankPassword = "";

        loginPage.inputUsername(username);
        loginPage.inputPassword(blankPassword);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Password is required");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithBlankUsernameAndBlankPassword(){

        String blankUsername = "";
        String blankPassword = "";

        loginPage.inputUsername(blankUsername);
        loginPage.inputPassword(blankPassword);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertEquals(loginPage.errorMessage.getText(), "Epic sadface: Username is required");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }


}
