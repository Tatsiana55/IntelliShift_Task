package IntelliShift.utilities;

import IntelliShift.custom_exceptions.NoSuchBrowserException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){}
    private static WebDriver driver;


    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("disable-gpu");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                default:
                    throw new NoSuchBrowserException("No Such Browser Available");
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
