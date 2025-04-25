package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverUtilities {

    private static WebDriver getEdgeDriver(){
        EdgeOptions options = new EdgeOptions();
        // options.addArguments("--headless=new");
        return new EdgeDriver(options);
    }

    private static WebDriver getChromeDriver(){
        return new ChromeDriver();
    }

    public static WebDriver getInitializedWebDriver(String browser){
        log.info(String.format("Initializing driver for browser: %s",browser));
        WebDriver driver;
        switch (browser.trim().toLowerCase()){
            case "edge":
                driver = DriverUtilities.getEdgeDriver();
                break;
            case "chrome":
                driver = DriverUtilities.getChromeDriver();
                break;
            default:
                throw new RuntimeException("Unknown driver type!");
        }
        log.info("Web driver initialized successfully.");
        return driver;
    }
}
