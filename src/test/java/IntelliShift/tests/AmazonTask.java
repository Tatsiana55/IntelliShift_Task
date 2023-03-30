package IntelliShift.tests;

import IntelliShift.pages.AmazonTaskPage;
import IntelliShift.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class AmazonTask {
    @BeforeClass
    public void navigateToAmazon(){
        Driver.getDriver().get("https://amazon.com");
    }

//    @AfterClass
//    public void tearUp(){
//        Driver.closeDriver();
//    }

    @Test
    public void gelPen() throws InterruptedException {
        String expectedTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);

        AmazonTaskPage amazonTaskPage = new AmazonTaskPage();

        //Search for “Gel Pen”.
        amazonTaskPage.getSearchBar().sendKeys("Gel Pen" + Keys.ENTER);

        //Validate all search suggestions contain “gel pens“ and choose last gel pen option from the suggestions.
        SoftAssert softAssert = new SoftAssert();
        for (WebElement each : amazonTaskPage.getSearchResultText()) {
            softAssert.assertTrue(each.getText().toLowerCase().contains("gel pens"));
        }
        amazonTaskPage.getSearchResultLinks().get(amazonTaskPage.getSearchResultLinks().size()-1).click();
        Driver.getDriver().navigate().back();

        //Check the search result ensuring every product item has the “Pen” in its title.
         for(WebElement each: amazonTaskPage.getSearchResultText()){
            softAssert.assertTrue(each.getText().contains("Pen"));
        }

         //Click on the item from that has the lowest price in the search list.
        amazonTaskPage.getSortByButton().click();
        amazonTaskPage.getSortLowToHigh().click();
        amazonTaskPage.getSearchResultLinks().get(1).click();

        //Change quantity to 2 then add to cart.
        amazonTaskPage.getQuantityDropDown().click();
        amazonTaskPage.getOption2DropDown().click();
        amazonTaskPage.getAddToCartButton().click();

        //Empty Cart.
        amazonTaskPage.getGoToCartButton().click();
        amazonTaskPage.getDeleteFromCartButton().click();

        //Validate “Your item was removed from shopping cart” message
        Assert.assertTrue(amazonTaskPage.getMessage().isDisplayed());






    }


}
