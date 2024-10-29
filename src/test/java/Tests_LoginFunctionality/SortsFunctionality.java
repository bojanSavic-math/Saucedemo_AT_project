package Tests_LoginFunctionality;

import Base.BaseTest;
import Base.Item;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SortsFunctionality extends BaseTest {

    public List<Item> databaseItems = new ArrayList<>();

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        itmesFromDatabase(databaseItems);
        String username = excelReader.getStringData("UsersCredentials", 1, 0);
        String password = excelReader.getStringData("UsersCredentials", 1, 1);
        logIn(username, password);
    }

    public static void sortItemsAlphabetically(List<Item> items){
        int n = items.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (items.get(j).getItemTitle().compareTo(items.get(min_idx).getItemTitle()) < 0) {
                    min_idx = j;
                }
            }
            Item temp = items.get(i);
            items.set(i, items.get(min_idx));
            items.set(min_idx, temp);
        }
    }

    public static void sortItemsByPriceLowToHigh(List<Item> items){
        int n = items.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                double price1 = Double.valueOf(items.get(j).getItemPrice().substring(1));
                double price2 = Double.valueOf(items.get(min_idx).getItemPrice().substring(1));
                if (price1 < price2) {
                    min_idx = j;
                }
            }
            Item temp = items.get(i);
            items.set(i, items.get(min_idx));
            items.set(min_idx, temp);
        }
    }

    public static void sortItemsByPriceHighToLow(List<Item> items){
        int n = items.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                double price1 = Double.valueOf(items.get(j).getItemPrice().substring(1));
                double price2 = Double.valueOf(items.get(min_idx).getItemPrice().substring(1));
                if (price1 > price2) {
                    min_idx = j;
                }
            }
            Item temp = items.get(i);
            items.set(i, items.get(min_idx));
            items.set(min_idx, temp);
        }
    }

    @Test
    public void productCanBeSortedAlphabeticallyFromAtoZ() {
        Select sortSelect = new Select(inventoryPage.sortDropdown);
        sortSelect.selectByVisibleText("Name (A to Z)");

        sortItemsAlphabetically(databaseItems);

        for (int i = 0; i < databaseItems.size(); i++) {
            Assert.assertEquals(databaseItems.get(i).getItemTitle(), inventoryPage.itemsNames.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemDescription(), inventoryPage.itemsDescriptions.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemImage(), inventoryPage.images.get(i).getAttribute("src"));
            Assert.assertEquals(databaseItems.get(i).getItemPrice(), inventoryPage.itemsPrices.get(i).getText());
        }
    }

    @Test
    public void productCanBeSortedAlphabeticallyFromZtoA() {
        Select sortSelect = new Select(inventoryPage.sortDropdown);
        sortSelect.selectByVisibleText("Name (Z to A)");

        sortItemsAlphabetically(databaseItems);

        int j = 0;
        for (int i = databaseItems.size()-1; i>=0; i--) {
            Assert.assertEquals(databaseItems.get(i).getItemTitle(), inventoryPage.itemsNames.get(j).getText());
            Assert.assertEquals(databaseItems.get(i).getItemDescription(), inventoryPage.itemsDescriptions.get(j).getText());
            Assert.assertEquals(databaseItems.get(i).getItemImage(), inventoryPage.images.get(j).getAttribute("src"));
            Assert.assertEquals(databaseItems.get(i).getItemPrice(), inventoryPage.itemsPrices.get(j).getText());
            j++;
        }
    }

    @Test
    public void productsCanBeSortedByPriceFromLowToHigh() {
        Select sortSelect = new Select(inventoryPage.sortDropdown);
        sortSelect.selectByVisibleText("Price (low to high)");

        sortItemsByPriceLowToHigh(databaseItems);

        for (int i = 0; i < databaseItems.size(); i++) {
            Assert.assertEquals(databaseItems.get(i).getItemPrice(), inventoryPage.itemsPrices.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemTitle(), inventoryPage.itemsNames.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemDescription(), inventoryPage.itemsDescriptions.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemImage(), inventoryPage.images.get(i).getAttribute("src"));
        }
    }

    @Test
    public void productsCanBeSortedByPriceFromHighToLow() {
        Select sortSelect = new Select(inventoryPage.sortDropdown);
        sortSelect.selectByVisibleText("Price (high to low)");

        sortItemsByPriceHighToLow(databaseItems);

        for (int i = 0; i < databaseItems.size(); i++) {
            Assert.assertEquals(databaseItems.get(i).getItemPrice(), inventoryPage.itemsPrices.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemTitle(), inventoryPage.itemsNames.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemDescription(), inventoryPage.itemsDescriptions.get(i).getText());
            Assert.assertEquals(databaseItems.get(i).getItemImage(), inventoryPage.images.get(i).getAttribute("src"));
        }

    }

}
