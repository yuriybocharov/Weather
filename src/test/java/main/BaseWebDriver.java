package main;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.Variables;

import java.util.concurrent.TimeUnit;

public class BaseWebDriver {
    public WebDriver driver;
    public WebDriverWait wait;

    {
        System.setProperty ("webdriver.chrome.driver", "C:\\Users\\Ura\\Downloads\\chromedriver.exe");
    }
    @BeforeClass(alwaysRun = true)
    public void start() {
        ChromeOptions options = new ChromeOptions ();
        options.addArguments ("--incognito");

        driver = new ChromeDriver (options);
        driver.manage ().window ().maximize ();
        driver.manage ().timeouts ().implicitlyWait (8, TimeUnit.SECONDS);
        driver.get (Variables.URL);
        wait = new WebDriverWait (driver, 10);

    }

    @AfterClass(alwaysRun = true)
    public void finish() {
        driver.quit ();
    }
}
