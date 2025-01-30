package pages;

import models.CarDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarRegLookupPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final By REG_INPUT = By.id("vrm-input");
    private static final By SEARCH_BUTTON = By.cssSelector("button[type='submit'] span[class='Button-module__label-SKEy']");
    private static final By MAKE_MODEL = By.cssSelector(".HeroVehicle__title-FAmG");
    private static final By MAKE_YEAR = By.cssSelector("div[class='Hero__homepageHeroWrapper-tQXt mileageTransitionStyles'] li:nth-child(1)");
    private static final By ERROR_MESSAGE = By.cssSelector(".HomepageVRM__component-CU1m");
    public CarRegLookupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        PageFactory.initElements(driver, this);
    }
    public CarDetails searchWithRegistration(String regNumber) {
        driver.get("https://motorway.co.uk/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(REG_INPUT)).sendKeys(regNumber);
        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON)).click();

        try {
            String makeModel = wait.until(ExpectedConditions.visibilityOfElementLocated(MAKE_MODEL)).getText();
            String year = wait.until(ExpectedConditions.visibilityOfElementLocated(MAKE_YEAR)).getText();
            return new CarDetails(makeModel, year);
        } catch (TimeoutException e) {
            return handleErrorCase();
        }
    }

    private CarDetails handleErrorCase() {
        try {
            if (driver.findElement(ERROR_MESSAGE).isDisplayed()) {
                return null;
            }
        } catch (NoSuchElementException ignored) {}
        throw new RuntimeException("Unexpected page state");
    }
}
