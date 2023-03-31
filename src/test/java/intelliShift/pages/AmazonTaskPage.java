package intelliShift.pages;

import intelliShift.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonTaskPage {
    public AmazonTaskPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='s-suggestion s-suggestion-ellipsis-direction']")
    private List<WebElement> suggestions;


    @FindBy(xpath = "//div[@class='left-pane-results-container']//div[1]")
    private WebElement suggestionFirstOption;

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBar;


    @FindBy (css = "[cel_widget_id^='MAIN-SEARCH_RESULTS']")
    private List<WebElement> searchResult;

    @FindBy(xpath = "//span[@id='a-autoid-0-announce']")
    private WebElement sortByButton;

    @FindBy(css = "#s-result-sort-select_1")
    private WebElement sortLowToHigh;

    @FindBy(xpath = "//span[@id='a-autoid-0-announce']")
    private WebElement quantityDropDown;

    public List<WebElement> getDropDownOptions() {
        return dropDownOptions;
    }

    @FindBy(xpath = "//span[@id='a-autoid-0-announce']/li")
    private List<WebElement> dropDownOptions;

    @FindBy(css = "a#quantity_1")
    private WebElement option2DropDown;

    @FindBy(css = "#add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@id = 'attach-sidesheet-view-cart-button']")
    private WebElement goToCartButton;

    @FindBy(css = "#sc-active-cart")
    private WebElement shoppingCartItem;


    @FindBy(xpath = "//span//input[@data-action='delete']")
    private WebElement deleteFromCartButton;

    @FindBy(xpath = "//h1")
    private WebElement message;

    public List<WebElement> getSuggestions() {
        return suggestions;
    }
    public WebElement getSuggestionFirstOption() {
        return suggestionFirstOption;
    }

    public WebElement getSearchBar() {
        return searchBar;
    }
    public List<WebElement> getSearchResult() {
        return searchResult;
    }

    public WebElement getSortByButton() {
        return sortByButton;
    }

    public WebElement getSortLowToHigh() {
        return sortLowToHigh;
    }

    public WebElement getQuantityDropDown() {
        return quantityDropDown;
    }

    public WebElement getOption2DropDown() {
        return option2DropDown;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public WebElement getGoToCartButton() {
        return goToCartButton;
    }
    public WebElement getShoppingCartItem() {
        return shoppingCartItem;
    }

    public WebElement getDeleteFromCartButton() {
        return deleteFromCartButton;
    }

    public WebElement getMessage() {
        return message;
    }



}
