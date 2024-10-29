package Tests_LoginFunctionality;

import Base.BaseTest;
import Base.Item;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class InventoryPageItemsDataTest extends BaseTest {

    public List<Item> databaseItems = new ArrayList<>();

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        itmesFromDatabase(databaseItems);
        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);
        logIn(username, password);
    }

    @Test
    public void itemsImagesMatchDatabaseItemsImages() {
        for (int i = 0; i < inventoryPage.images.size(); i++) {
            WebElement image = inventoryPage.images.get(i);
            Assert.assertEquals(image.getAttribute("src"), databaseItems.get(i).getItemImage());
        }
    }

    @Test
    public void itemsNamesMatchDatabaseItemsNames() {
        for (int i = 0; i < inventoryPage.itemsNames.size(); i++) {
            Assert.assertEquals(inventoryPage.itemsNames.get(i).getText(), databaseItems.get(i).getItemTitle());
        }
    }

    @Test
    public void itemsDescriptionsMatchDatabaseItemsDescriptions() {
        for (int i = 0; i < inventoryPage.itemsDescriptions.size(); i++) {
            Assert.assertEquals(inventoryPage.itemsDescriptions.get(i).getText(), databaseItems.get(i).getItemDescription());
        }
    }

    @Test
    public void itemsPricesMatchDatabaseItemsPrices() {
        for (int i = 0; i < inventoryPage.itemsPrices.size(); i++) {
            Assert.assertEquals(inventoryPage.itemsPrices.get(i).getText(), databaseItems.get(i).getItemPrice());
        }
    }

}
