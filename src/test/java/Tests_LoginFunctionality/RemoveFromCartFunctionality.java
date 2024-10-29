package Tests_LoginFunctionality;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveFromCartFunctionality extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);
        logIn(username, password);
        addItemsToCart();}

    @Test
    public void userCanRemoveAddedItemsFromCart() {
        cartPage.clickOnRemoveButtons();

        Assert.assertEquals(cartPage.cartItems.size(), 0);
        boolean isPresentCartBagde = false;
        try {
            WebElement cart_badge = driver.findElement(By.className("shopping_cart_badge"));
            isPresentCartBagde = true;
        } catch (Exception e){
            System.out.println("");
        }

        Assert.assertFalse(isPresentCartBagde);
    }

}
