package Tests_LoginFunctionality;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ChekoutFunctiionality extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);
        logIn(username, password);
        addItemsToCart();}

    @Test
    public void userCanContinuePurchaseFromCartAndInputValidPersonalData() {

        int n = cartPage.cartItems.size();
        String[] titles = new String[n];
        String[] descriptions = new String[n];
        String[] prices = new String[n];

        for(int i = 0; i < n; i++){
            titles[i] = inventoryPage.itemsNames.get(i).getText();
            descriptions[i] = inventoryPage.itemsDescriptions.get(i).getText();
            prices[i] = inventoryPage.itemsPrices.get(i).getText();
        }


        String firstName = "Alan";
        String lastName = "Turing";
        String postalCode = "42042";
        continuePurchaseFromCart(firstName, lastName, postalCode);

        Assert.assertTrue(checkoutPage.firstNameField.getAttribute("value").equals(firstName));
        Assert.assertTrue(checkoutPage.lastNameField.getAttribute("value").equals(lastName));
        Assert.assertTrue(checkoutPage.postalCodeField.getAttribute("value").equals(postalCode));

        checkoutPage.clickOnContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");

        for(int i = 0; i < inventoryPage.itemsNames.size(); i++){
            Assert.assertEquals(inventoryPage.itemsNames.get(i).getText(), titles[i]);
            Assert.assertEquals(inventoryPage.itemsDescriptions.get(i).getText(), descriptions[i]);
            Assert.assertEquals(inventoryPage.itemsPrices.get(i).getText(), prices[i]);
        }

    }

    @Test
    public void userCanFinishPurchase() {

        String firstName = "Alan";
        String lastName = "Turing";
        String postalCode = "42042";
        continuePurchaseFromCart(firstName, lastName, postalCode);

        Assert.assertTrue(checkoutPage.firstNameField.getAttribute("value").equals(firstName));
        Assert.assertTrue(checkoutPage.lastNameField.getAttribute("value").equals(lastName));
        Assert.assertTrue(checkoutPage.postalCodeField.getAttribute("value").equals(postalCode));

        checkoutPage.clickOnContinueButton();

        double sum = 0;
        for (int i = 0; i < inventoryPage.itemsPrices.size(); i++){
            double prices = Double.valueOf(inventoryPage.itemsPrices.get(i).getText().substring(1));
            sum += prices;
        }
        sum = sum + Double.valueOf(checkoutPage.tax.getText().substring(6));

        BigDecimal bd = new BigDecimal(Double.toString(sum));
        BigDecimal zaokruzen = bd.setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(checkoutPage.summaryTotal.getText().substring(7), "$"+zaokruzen);

        checkoutPage.clickOnFinishButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(checkoutPage.backHomeButton.isDisplayed());
        Assert.assertEquals(checkoutPage.checkoutCompleteMessage.getText(), "Thank you for your order!");
    }


}
