package Tests_LoginFunctionality;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartFunctionality extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");

        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);
        logIn(username, password);
    }

    @Test
    public void userCanAddItemsToCartFromInventoryPage() {

        int[] items = {0, 2, 3, 5};//mora rastuci

        inventoryPage.addMultipleItemsToCart(items);
        scrollToElement(inventoryPage.cartBadge);

        //uspesno azuriran broj na koripici
        Assert.assertEquals(inventoryPage.cartBadge.getText(), String.valueOf(items.length));

        //Remove dugmica ima onoliko koji je bilo  klikova na Add dugme
        Assert.assertEquals(inventoryPage.removeButtons.size(), items.length);



        //u korpi su bas kliknuti proizvodi
        int[] itemsCopy = {0, 2, 3, 5};
        String[] titles = new String[items.length];
        String[] descriptions = new String[items.length];
        String[] prices = new String[items.length];

        for(int i = 0; i < itemsCopy.length; i++){
            titles[i] = inventoryPage.itemsNames.get(itemsCopy[i]).getText();
            descriptions[i] = inventoryPage.itemsDescriptions.get(itemsCopy[i]).getText();
            prices[i] = inventoryPage.itemsPrices.get(itemsCopy[i]).getText();
        }

        inventoryPage.clickOnCartBadge();

        for(int i = 0; i < inventoryPage.itemsNames.size(); i++){
            Assert.assertEquals(inventoryPage.itemsNames.get(i).getText(), titles[i]);
            Assert.assertEquals(inventoryPage.itemsDescriptions.get(i).getText(), descriptions[i]);
            Assert.assertEquals(inventoryPage.itemsPrices.get(i).getText(), prices[i]);
        }
    }

    @Test
    public void userCanAddItemToCartFromItemPage() {

        for(int i = 0; i<inventoryPage.itemsNames.size(); i++) {
            String itemTitle = inventoryPage.itemsNames.get(i).getText();
            String itemDescription = inventoryPage.itemsDescriptions.get(i).getText();
            String itemPrice = inventoryPage.itemsPrices.get(i).getText();

            inventoryPage.clickOnItemName(i);

            inventoryPage.addOneItemToCart();
            scrollToElement(inventoryPage.cartBadge);

            //uspesno azuriran broj na koripici
            Assert.assertEquals(inventoryPage.cartBadge.getText(), "1");

            //Imamo samo jedno Remove dugme
            Assert.assertEquals(inventoryPage.removeButtons.size(), 1);

            //Na Item strnici je bas kliknuti proizvod
            Assert.assertEquals(itemTitle, itemPage.itemTitle.getText());

            //U korpi je bas kliknuti proizvod
            inventoryPage.clickOnCartBadge();
            Assert.assertEquals(inventoryPage.itemsNames.get(0).getText(), itemTitle);
            Assert.assertEquals(inventoryPage.itemsDescriptions.get(0).getText(), itemDescription);
            Assert.assertEquals(inventoryPage.itemsPrices.get(0).getText(), itemPrice);
        }

    }

}
