package IntelliShift.pages;

import IntelliShift.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonTaskPage {
    public AmazonTaskPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBar;

    @FindBy(css = "#a-autoid-0-announce")
    private WebElement sortByButton;

    @FindBy(xpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']")
    private List<WebElement> searchResultText;

    @FindBy(xpath = "//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-4']//a")
    private List<WebElement> searchResultLinks;

    @FindBy(css = "#s-result-sort-select_1")
    private WebElement sortLowToHigh;

    @FindBy(xpath = "//span[@class='a-price']//span[@class='a-offscreen']")
    private WebElement getPrice;

    @FindBy(xpath = "//span[@class='a-button-inner']//span[@id='a-autoid-0-announce'")
    private WebElement quantityDropDown;

    @FindBy(css = "a#quantity_2")
    private WebElement option2DropDown;

    @FindBy(css = "#add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span//a[@data-csa-c-content-id='sw-gtc_CONTENT']")
    private WebElement goToCartButton;


    @FindBy(xpath = "//span//input[@data-action='delete']")
    private WebElement deleteFromCartButton;

    @FindBy(xpath = "//div[@id='sc-active-cart']//h1")
    private WebElement message;

    public WebElement getSearchBar() {
        return searchBar;
    }

    public WebElement getSortByButton() {
        return sortByButton;
    }

    public List<WebElement> getSearchResultText() {
        return searchResultText;
    }

    public List<WebElement> getSearchResultLinks() {
        return searchResultLinks;
    }
    public WebElement getSortLowToHigh() {
        return sortLowToHigh;
    }

    public WebElement getGetPrice() {
        return getPrice;
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

    public WebElement getDeleteFromCartButton() {
        return deleteFromCartButton;
    }

    public WebElement getMessage() {
        return message;
    }



}