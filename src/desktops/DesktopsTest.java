package desktops;

import homepage.TopMenuTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DesktopsTest extends TopMenuTest {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        //1.1 Mouse hover on Desktops Tab.and click
        mouseHover(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]"));

        //1.2 Click on “Show All Desktops”
        selectMenu("Show All Desktops");

        //1.3 Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (Z - A)");

        //1.4 Verify the Product will arrange in Descending order.

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //2.1 Mouse hover on Desktops Tab.and click
        mouseHover(By.xpath("//body/div[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]"));
        Thread.sleep(3000);

        //2.2 Click on “Show All Desktops”
        selectMenu("Show All Desktops");

        //2.3 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (Z - A)");

        //2.4 Select product “HP LP3065”
        clickOnElement(By.xpath("//a[contains(text(),'HP LP3065')]"));

        //2.5 Verify the Text "HP LP3065"
        verifyText("HP LP3065", By.xpath("//a[contains(text(),'HP LP3065')]"), "Text not matched");

        //2.6 Select Delivery Date "2022-11-30"
        String year = "2022";
        String month = "November";
        String date = "30";
        clickOnElement(By.xpath("//i[@class='fa fa-calendar']"));
        while (true) {
            String monthYear = driver.findElement(By.xpath("//th[@class='picker-switch']")).getText();
            //2011-04-22
            String arr[] = monthYear.split(" ");
            String mon = arr[0];
            String yr = arr[1];
            //select expected year
            if (mon.equalsIgnoreCase(month) && yr.equals(year))
                break;
            else
                clickOnElement(By.xpath("//body[1]/div[4]/div[1]/div[1]/table[1]/thead[1]/tr[1]/th[3]"));
        }
        //select date
        List<WebElement> allDates = driver.findElements(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[5]/td[3]"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }

        //2.7 Enter Qty "1" using Select class
        driver.findElement(By.xpath("//input[@id='input-quantity']")).clear();
        sendTextToElement(By.xpath("//input[@id='input-quantity']"), "1");
        Thread.sleep(2000);

        //2.8 Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        Thread.sleep(2000);

        //2.9 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        verifyText("Success: You have added HP LP3065 to your shopping cart!\n×",
                By.xpath("//div[text()='Success: You have added ']"),
                "Text not matched");
        Thread.sleep(2000);

        //2.10 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        Thread.sleep(2000);

        //2.11 Verify the text "Shopping Cart"
        verifyText("Shopping Cart", By.xpath("//a[contains(text(),'Shopping Cart')]"), "Text not matched");
        Thread.sleep(2000);

        //2.12 Verify the Product name "HP LP3065"
        verifyText("HP LP3065", By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"), "Text not matched");
        Thread.sleep(2000);

        //2.13 Verify the Delivery Date "2022-11-30"
        verifyText("Delivery Date: 2022-11-30", By.xpath("//small[contains(text(),'Delivery Date: 2022-11-30')]"), "Date not verified");
        Thread.sleep(2000);

        //2.14 Verify the Model "Product21"
        verifyText("Product 21", By.xpath("//td[contains(text(),'Product 21')]"), "Text not matched");
        Thread.sleep(2000);

        //2.15 Verify the Total "£74.73"
        clickOnElement(By.xpath("//span[contains(text(),'Currency')]"));
        clickOnElement(By.xpath("//button[contains(text(),'£ Pound Sterling')]"));
        /*List<WebElement> manageCurrency = driver.findElements(By.xpath("//body/nav[@id='top']/div[1]/div[1]/form[1]/div[1]/ul[1]"));

        for (WebElement element : manageCurrency) {
            if (element.getText().equals("£ Pound Sterling")) {
                element.click();
            }
        }*/
        verifyText("£74.73", By.xpath("//body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[4]/td[2]"), "Total not verified");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
