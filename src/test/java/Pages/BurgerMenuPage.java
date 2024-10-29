package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerMenuPage extends BaseTest {

    public BurgerMenuPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    public void clickOnLogoutButton() {
        logoutButton.click();
    }

    //react-burger-cross-btn
    @FindBy(id = "react-burger-cross-btn")
    public WebElement xButtonHamburberMenu;

    public void closeHamburgerMenu() {
        xButtonHamburberMenu.click();
    }
}
