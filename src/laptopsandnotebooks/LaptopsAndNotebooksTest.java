package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws NumberFormatException {
        //1.1 Mouse hover on Laptops & Notebooks Tab
        mouseHover(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[2]/a[1]"));
        //1.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[contains(text(),'Show All Laptops & Notebooks')]"));

        //1.4 Verify the Product price will arrange in High to Low order.
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement element : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(element.getText().replace("$", "")));
        }
        //1.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");

        List<WebElement> afterFilterPrice = driver.findElements(By.className("price"));
        List<Double> afterFilterPriceList = new ArrayList<>();
        for (WebElement element : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(element.getText().replace("$", "")));
        }

        Collections.sort(beforeFilterPriceList);        //list will be sorted in ascending order
        Collections.reverse(beforeFilterPriceList);     //list will be sorted in descending order
        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);
        //Thread.sleep(3000);
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHover(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[2]/a[1]"));
        //2.2 Click on “Show All Laptops & Notebooks”
        clickOnElement(By.xpath("//a[contains(text(),'Show All Laptops & Notebooks')]"));
        //2.3 Select Sort By "Price (High > Low)"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
        //2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//a[text()='MacBook']"));
        //2.5 Verify the text “MacBook”
        verifyText("MacBook", (By.xpath("//h1[contains(text(),'MacBook')]")), "Text not matched");
        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.id("button-cart"));
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        verifyText("Success: You have added MacBook to your shopping cart!\n×", (By.xpath("//div[@class='alert alert-success alert-dismissible']")), "Message not verified");
        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        //2.9 Verify the text "Shopping Cart"
        verifyText("Shopping Cart", (By.xpath("//a[contains(text(),'Shopping Cart')]")), "Text not found");
        //2.10 Verify the Product name "MacBook"
        verifyText("MacBook", (By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")), "");
        //2.11 Change Quantity "2"
        driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]")).clear();
        sendTextToElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/input[1]"), "2");
        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//tbody/tr[1]/td[4]/div[1]/span[1]/button[1]"));
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        verifyText("Success: You have modified your shopping cart!\n×", (By.xpath("//div[@class='alert alert-success alert-dismissible']")), "");
        //
        clickOnElement(By.xpath("//span[contains(text(),'Currency')]"));
        clickOnElement(By.xpath("//button[contains(text(),'£ Pound Sterling')]"));
        //2.14 Verify the Total £737.45
        verifyText("£737.45", (By.xpath("//tbody/tr[1]/td[6]")), "");
        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[contains(text(),'Checkout')]"));
        //2.16 Verify the text “Checkout”
        verifyText("Checkout", (By.xpath("//h1[contains(text(),'Checkout')]")), "");
        //2.17 Verify the Text “New Customer”
        Thread.sleep(3000);
        verifyText("New Customer", (By.xpath("//h2[contains(text(),'New Customer')]")), "");
        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/label[1]"));
        //2.19 Click on “Continue” tab
        clickOnElement(By.xpath("//input[@id='button-account']"));
        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Aaa");
        sendTextToElement(By.id("input-payment-lastname"), "Bbb");
        sendTextToElement(By.id("input-payment-email"), "test123@gmail.com");
        sendTextToElement(By.id("input-payment-telephone"), "9876543210");
        sendTextToElement(By.id("input-payment-address-1"), "1 A street");
        sendTextToElement(By.id("input-payment-city"), "London");
        sendTextToElement(By.id("input-payment-postcode"), "EC1A 2AA");
        selectByVisibleTextFromDropDown(By.id("input-payment-country"), "Aaland Islands");
        selectByVisibleTextFromDropDown(By.id("input-payment-zone"), " --- None --- ");
        Thread.sleep(2000);
        //2.21 Click on “Continue” Button
        clickOnElement(By.id("button-guest"));
        //2.22 Add Comments About your order into text area
        sendTextToElement(By.name("comment"), "Please consider my order ASAP");
        //2.23 Check the Terms & Conditions check box
        clickOnElement(By.name("agree"));
        //2.24 Click on “Continue” button
        clickOnElement(By.id("button-payment-method"));
        //2.25 Verify the message “Warning: Payment method required!"
        verifyText("Warning: Payment method required!\n×", (By.xpath("//div[@class='alert alert-danger alert-dismissible']")), "");
    }

    @After
    public void testDown() {
        //closeBrowser();
    }
}