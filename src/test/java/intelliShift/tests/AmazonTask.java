package intelliShift.tests;

import intelliShift.pages.AmazonTaskPage;
import intelliShift.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static intelliShift.utilities.TestData.*;

public class AmazonTask {
    private final AmazonTaskPage amazonTaskPage = new AmazonTaskPage();
    private final WebDriverWait wait = new WebDriverWait(Driver.getDriver(),DEFAULT_WAIT);
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
        //Verify Title of the page

        Assert.assertEquals(Driver.getDriver().getTitle(), EXPECTED_TITLE);

         //Search for “Gel Pen”.
        amazonTaskPage.getSearchBar().sendKeys("Gel Pen");
        try {
            wait.until(ExpectedConditions.visibilityOf(amazonTaskPage.getSuggestionFirstOption()));
        }catch(StaleElementReferenceException e){

        }

        //Validate all search suggestions contain “gel pen“ and choose last gel pen option from the suggestions.
        for(WebElement each : amazonTaskPage.getSuggestions()){
            wait.until(ExpectedConditions.visibilityOf(each));
           Assert.assertTrue(each.getText().contains("gel pen"));

        }
        amazonTaskPage.getSuggestions().get(amazonTaskPage.getSuggestions().size()-1).click();

        //Check the search result ensuring every product item has the “Pen” in its title.
         SoftAssert softAssert = new SoftAssert();
         for(WebElement each: amazonTaskPage.getSearchResult()){
            softAssert.assertTrue(each.getText().contains("Pen"));

        }
        List<Integer> prices = new ArrayList<>();
        for (WebElement allProduct : amazonTaskPage.getSearchResult()) {
            // System.out.println(allProduct.getText());
            //System.out.println(allProduct.findElement(By.className("a-price")).getText());
            int productPrice = Integer.parseInt(allProduct.findElement(By.className("a-price-whole")).getText() +
                    allProduct.findElement(By.className("a-price-fraction")).getText());
            prices.add(productPrice);
        }
        Integer minPrice = prices.stream().min(Integer::compare).get();
        int lowestPriceIndex = prices.indexOf(minPrice);
        wait.until(ExpectedConditions.elementToBeClickable(amazonTaskPage.getSearchResult().get(lowestPriceIndex)));
        amazonTaskPage.getSearchResult().get(lowestPriceIndex).click();

//         //Click on the item from that has the lowest price in the search list.
//        amazonTaskPage.getSortByButton().click();
//        amazonTaskPage.getSortLowToHigh().click();
//        Assert.assertTrue(amazonTaskPage.getSortByButton().getText().contains("Price:Low to High"));
//        amazonTaskPage.getAllProducts().get(0).click();
//
        //Change quantity to 2 then add to cart.
        wait.until(ExpectedConditions.visibilityOf(amazonTaskPage.getQuantityDropDown()));
        amazonTaskPage.getQuantityDropDown().click();
        amazonTaskPage.getOption2DropDown().click();
        Assert.assertTrue(amazonTaskPage.getOption2DropDown().getAttribute("class").contains("active"));
        amazonTaskPage.getAddToCartButton().click();

        //Empty Cart.
        amazonTaskPage.getGoToCartButton().click();
        amazonTaskPage.getDeleteFromCartButton().click();

        //Validate “Your item was removed from shopping cart” message

        wait.until(ExpectedConditions.textToBePresentInElement(amazonTaskPage.getMessage(),"Your Amazon Cart is empty."));
        Assert.assertEquals(amazonTaskPage.getMessage().getText(), EXPECTED_MESSAGE);






    }


}
