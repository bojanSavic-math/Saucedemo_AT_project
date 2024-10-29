package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BaseTest {

    public ItemPage() {
        PageFactory.initElements(driver, this);
    }

    //inventory_details_name large_size
    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement itemTitle;



}
