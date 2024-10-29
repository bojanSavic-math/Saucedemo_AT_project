package Base;

import Pages.*;
import Tests_LoginFunctionality.AddToCartFunctionality;
import Tests_LoginFunctionality.LoginFunctionality;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BaseTest {

    public static WebDriver driver;
    public ExcelReader excelReader;
    public WebDriverWait wait;

    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public BurgerMenuPage burgerMenuPage;
    public ItemPage itemPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;

    public LoginFunctionality loginFunctionality;
    public AddToCartFunctionality addToCartFunctionality;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));


        excelReader = new ExcelReader("miniSaucedemoDatabase.xlsx");
        //login = new Login();


        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        burgerMenuPage = new BurgerMenuPage();
        itemPage = new ItemPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();

        loginFunctionality = new LoginFunctionality();
        addToCartFunctionality = new AddToCartFunctionality();

    }

    public void logIn(String username, String password){
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

    }

    public void addItemsToCart() {

        int[] items = {0, 2, 3, 5};//mora rastuci

        inventoryPage.addMultipleItemsToCart(items);
        scrollToElement(inventoryPage.cartBadge);
        inventoryPage.clickOnCartBadge();
    }


    public void itmesFromDatabase(List<Item> items) {
        for(int i = 0; i < 6; i++) {
            String name = excelReader.getStringData("ItemsData", i+1, 1);
            String description = excelReader.getStringData("ItemsData", i+1, 2);
            String price = "$"+excelReader.getStringData("ItemsData", i+1, 3);
            String image = "https://www.saucedemo.com" + excelReader.getStringData("ItemsData", i+1, 4);

            Item item = new Item(name, description, price, image);
            items.add(item);
        }
    }

    public void continuePurchaseFromCart( String firstName, String lastName, String postalCode) {

        scrollToElement(cartPage.checkoutButton);
        cartPage.clickOnCheckOutButton();

        checkoutPage.inputFirstName(firstName);
        checkoutPage.inputLastName(lastName);
        checkoutPage.inputPostalCode(postalCode);

    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

   /* @AfterClass
    public void tearDown() {
        driver.quit();
    }
    */
}
