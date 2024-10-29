package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "continue")
    public WebElement continueButton;

    public void inputFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        //firstNameField.sendKeys(Keys.ENTER);
    }

    public void inputLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }

    @FindBy(id = "finish")
    public WebElement finishButton;

    public void clickOnFinishButton() {
        scrollToElement(finishButton);
        finishButton.click();
    }

    //back-to-products
    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    //complete-header
    @FindBy(className = "complete-header")
    public WebElement checkoutCompleteMessage;

    //summary_total_label
    @FindBy(className =  "summary_total_label")
    public WebElement summaryTotal;

    //summary_tax_label
    @FindBy(className =  "summary_tax_label")
    public WebElement tax;
}
