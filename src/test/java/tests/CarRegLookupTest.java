package tests;

import models.CarDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CarRegLookupPage;
import utils.FileParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CarRegLookupTest {
    private WebDriver driver;
    private CarRegLookupPage carRegLookupPage;
    private Map<String, CarDetails> expectedDetails;

    @BeforeClass
    public void setup() throws IOException {
//        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        carRegLookupPage = new CarRegLookupPage(driver);
        expectedDetails = FileParser.parseOutputFile("car_output.txt");
    }

    @DataProvider(name = "regData")
    public Object[][] provideRegData() throws IOException {
        List<String> regNumbers = FileParser.parseInputFile("car_input.txt");
        return regNumbers.stream()
                .map(reg -> new Object[]{reg, expectedDetails.get(reg)})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "regData")
    public void testCarReg(String regNumber, CarDetails expected) {
        CarDetails actual = carRegLookupPage.searchWithRegistration(regNumber);
        if (expected == null) {
            Assert.assertNull(actual, "Unexpected details found for registration: " + regNumber);
        } else {
            Assert.assertEquals(actual, expected, "Details mismatch for registration: " + regNumber);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
