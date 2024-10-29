package Tests_LoginFunctionality;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutFunctionality extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void userCanLogOut() {
        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);
        logIn(username, password);

        inventoryPage.clickOnHambuergerManu();
        wait.until(ExpectedConditions.visibilityOf(burgerMenuPage.logoutButton));
        burgerMenuPage.clickOnLogoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.logo.getText(), "Swag Labs");
    }

    @Test
    public void userRemainsLoggedOutAfterThePageIsRefreshed() {
        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);
        logIn(username, password);

        inventoryPage.clickOnHambuergerManu();
        wait.until(ExpectedConditions.visibilityOf(burgerMenuPage.logoutButton));
        burgerMenuPage.clickOnLogoutButton();
        driver.navigate().refresh();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.logo.getText(), "Swag Labs");
    }

}
