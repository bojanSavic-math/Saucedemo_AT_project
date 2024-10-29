package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    public WebElement header;

    public String getHeaderText() {
        return header.getText();
    }

    @FindBy(className = "bm-burger-button")
    public WebElement hamburgerMenu;

    public void clickOnHambuergerManu() {
        hamburgerMenu.click();
    }

    //btn btn_primary btn_small btn_inventory
    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> addToCartButtons;

    public void addOneItemToCart() {
        addToCartButtons.get(0).click();
    }

    public void addMultipleItemsToCart(int[] niz) {
        for(int i = 0; i < niz.length; i++) {
            for (int j = 0; j < addToCartButtons.size(); j++) {
                if (j == niz[i]) {
                    scrollToElement(addToCartButtons.get(j));
                    addToCartButtons.get(j).click();
                    for(int k = i + 1; k < niz.length; k++)
                        niz[k]--;
                }
            }
        }
    }


    //shopping_cart_badge
    @FindBy(className = "shopping_cart_badge")
    public WebElement cartBadge;

    public void clickOnCartBadge() {
        cartBadge.click();
    }

/*    //inventory_item
    @FindBy(className = "inventory_item")
    public List<WebElement> items;

    public void clikOnItem(int i) {
        scrollToElement(items.get(i));
        items.get(i).click();
    }*/


    //btn btn_secondary btn_small btn_inventory
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public List<WebElement> removeButtons;

    //inventory_item_name
    @FindBy(className = "inventory_item_name")
    public List<WebElement> itemsNames;

    public void clickOnItemName(int i) {
        scrollToElement(itemsNames.get(i));
        itemsNames.get(i).click();
    }

    //inventory_item_img
    @FindBy(css = "img[class='inventory_item_img']")
    public List<WebElement> images;

    //.inventory_item_desc
    @FindBy(className = "inventory_item_desc")
    public List<WebElement> itemsDescriptions;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemsPrices;

    //product_sort_container
    @FindBy(className = "product_sort_container")
    public WebElement sortDropdown;

    public void clickOnSortDropdown() {
        sortDropdown.click();
    }

    @FindBy(className = "product_sort_container")
    public WebElement SortDropdown;
    // Kreiraj Select objekat
    //Select sortSelect = new Select(sortDropdown);

    // Izaberi opciju iz padajućeg menija (npr. "Price (low to high)")
          //  sortSelect.selectByVisibleText("Price (low to high)");
}
