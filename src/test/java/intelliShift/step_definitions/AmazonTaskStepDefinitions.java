package intelliShift.step_definitions;

import intelliShift.pages.AmazonTaskPage;
import intelliShift.utilities.BrowserUtils;
import intelliShift.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static intelliShift.utilities.TestData.*;

public class AmazonTaskStepDefinitions {

    AmazonTaskPage amazonTaskPage = new AmazonTaskPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(),DEFAULT_WAIT);
    SoftAssert softAssert = new SoftAssert();


    @Given("user navigates to Amazon page")
    public void userNavigatesToAmazonPage() {
        Driver.getDriver().get(AMAZON_URL);
        Assert.assertEquals(Driver.getDriver().getTitle(), EXPECTED_TITLE);
    }

    @When("user puts into search bar {string}")
    public void userPutsIntoSearchBar(String itemName) {
        itemName = ITEM_NAME;
        amazonTaskPage.getSearchBar().sendKeys(itemName);
        try {
            wait.until(ExpectedConditions.visibilityOf(amazonTaskPage.getSuggestionFirstOption()));
        } catch (StaleElementReferenceException e) {
        }
    }

    @And("verifies all suggestions contain {string}")
    public void verifiesAllSuggestionsContain(String itemName) {
        try{
        for(WebElement each : amazonTaskPage.getSuggestions()) {
            wait.until(ExpectedConditions.visibilityOf(each));
            Assert.assertTrue(each.getText().contains(itemName));
        }
        }catch (StaleElementReferenceException a){
        }
    }

    @When("user chooses last suggestions option")
    public void user_chooses_last_suggestions_option() {
        amazonTaskPage.getSuggestions().get(amazonTaskPage.getSuggestions().size()-1).click();

    }

    @And("user verifies all search result have {string} in its  title")
    public void userVerifiesAllSearchResultHaveInItsTitle(String word) {
        word=WORD;
        for(WebElement each: amazonTaskPage.getSearchResult()){
            softAssert.assertTrue(each.getText().contains(word));

        }
    }

    @When("user clicks on the item that has the lowest price")
    public void user_clicks_on_the_item_that_has_the_lowest_price_changes_quantity_to_adds_item_to_the_cart() {
        //using Integer in this step because it does not impact comparing
        List<Integer> prices = new ArrayList<>();
        for (WebElement allProduct : amazonTaskPage.getSearchResult()) {
            int productPrice = Integer.parseInt(allProduct.findElement(By.className("a-price-whole")).getText() +
                    allProduct.findElement(By.className("a-price-fraction")).getText());
            prices.add(productPrice);
        }
        Integer minPrice = prices.stream().min(Integer::compare).get();
        int lowestPriceIndex = prices.indexOf(minPrice);
        wait.until(ExpectedConditions.elementToBeClickable(amazonTaskPage.getSearchResult().get(lowestPriceIndex)));
        BrowserUtils.clickWithJSExecutor(amazonTaskPage.getSearchResult().get(lowestPriceIndex));

    }

    @And("changes quantity to two, adds item to the cart")
    public void changesQuantityToTwoAddsItemToTheCart() {
        wait.until(ExpectedConditions.visibilityOf(amazonTaskPage.getQuantityDropDown()));
        amazonTaskPage.getQuantityDropDown().click();
        amazonTaskPage.getOption2DropDown().click();
        Assert.assertTrue(amazonTaskPage.getOption2DropDown().getAttribute("class").contains("active"));
        amazonTaskPage.getAddToCartButton().click();

    }

    @Then("user empty cart")
    public void user_empty_cart() {
        amazonTaskPage.getGoToCartButton().click();
        amazonTaskPage.getDeleteFromCartButton().click();

    }

    @Then("verifies the message")
    public void verifies_the_message() {
        wait.until(ExpectedConditions.textToBePresentInElement(amazonTaskPage.getMessage(),"Your Amazon Cart is empty."));
        Assert.assertEquals(amazonTaskPage.getMessage().getText(), EXPECTED_MESSAGE);

    }



}
