package ru.sberbank.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class, 'header_all_logo_contacts')]//div[@class='region-list']//a")
    private WebElement regionListLink;

    @FindBy(xpath = "//div[@class='kit-modal-body']//input[@class='kit-input__control']")
    private WebElement fieldSearchRegion;

    @FindBy(xpath = "//div[@class='footer-info']")
    private WebElement footer;

    @FindBy(xpath = "//ul[@class='social__list']//a")
    private List<WebElement> socialList;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40, 500);
        PageFactory.initElements(driver, this);
    }

    public void clickLinkRegionList() {
        component(this.regionListLink).click();
    }

    public void inputValueInFieldSearchRegion(final String region) {
        WebElement input = component(this.fieldSearchRegion);
        input.sendKeys(region);
    }

    public void selectValueRegion() {
        Action enter = new Actions(driver)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build();
        enter.perform();
    }

    public String valueAttributRegion(final String attribut) {
        WebElement link = component(this.regionListLink);
        return link.getAttribute(attribut);
    }

    public void scrollToSocialList() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", footer);
    }

    public List<String> valuesAttributSocialItem(final String attribute) {
        List<String> values = new ArrayList<>();
        for (WebElement item : socialList) {
            values.add(item.getAttribute(attribute));
        }
        return values;
    }

    private WebElement component(final WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
