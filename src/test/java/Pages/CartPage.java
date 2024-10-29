package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    //btn btn_secondary btn_small cart_button
    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeButtons;

    public void clickOnRemoveButtons() {
        int i = removeButtons.size();
        while(i > 0) {
            removeButtons.get(0).click();
            i--;
        }
    }

    //cart_item
    @FindBy(className = "cart_item")
    public List<WebElement> cartItems;


    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    public void clickOnCheckOutButton() {
        scrollToElement(checkoutButton);
        checkoutButton.click();
    }


}
