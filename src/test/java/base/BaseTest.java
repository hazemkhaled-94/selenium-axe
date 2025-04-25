package base;

import listeners.MethodListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.DriverUtilities;

@Slf4j
@Listeners(MethodListener.class)
public abstract class BaseTest {

    protected WebDriver driver;

    protected SoftAssert softAssert;

    @BeforeMethod
    public void setSoftAssert(){
        this.softAssert = new SoftAssert();
    }

    @AfterMethod
    public void assertAll(){
        this.softAssert.assertAll();
    }

    @BeforeTest
    public void initializeDriver(){
        this.driver = DriverUtilities.getInitializedWebDriver("Edge");
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

}
