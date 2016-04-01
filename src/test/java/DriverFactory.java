package test.java;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final String IE = "ie";
    private static final String DEFAULT = "chrome";


    public static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver driver;

    @BeforeSuite
    public void Setup() {
        // Uses chrome driver by default
        String browser = System.getenv("BROWSER");
        if (browser == null ) {
            browser =  DEFAULT;
        }

        if (browser.toLowerCase().equals(FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.toLowerCase().equals(EDGE)) {
            EdgeDriverManager.getInstance().setup();
            driver = new EdgeDriver();
        } else if(browser.toLowerCase().equals(IE)) {
            InternetExplorerDriverManager.getInstance().setup();
            driver = new InternetExplorerDriver();
        } else {
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
        }
    }

    @AfterSuite
    public void TearDown() {
        driver.close();
    }
}