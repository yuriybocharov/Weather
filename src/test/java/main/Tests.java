package main;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.*;
import pages.Variables;
import getDate.FormatDate;
import tools.WeatherCity;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static tools.CreatMassege.sendMassege;



public class Tests {
    public WebDriver driver;
    public WebDriverWait wait;

    {
        System.setProperty ("webdriver.chrome.driver", "C:\\Users\\Ura\\Downloads\\chromedriver.exe");
    }
    public static final String topic() {
        return Variables.TOPIC += WeatherCity.CYTI + " " + FormatDate.time;
    }

    String text = null;
    String letter = "";


    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.findElement (By.xpath (MailPage.LOGIN)).sendKeys (Variables.LOGIN);
        driver.findElement (By.xpath (MailPage.IDENTIFIER_NEXT)).click ();
        driver.findElement (By.xpath (MailPage.PASSWORD)).sendKeys (Variables.PASSWORD);
        wait.until (ExpectedConditions.elementToBeClickable (By.xpath (MailPage.PASSWORD_NEXT)));
        ((JavascriptExecutor) driver).executeScript ("arguments[0].click();", driver.findElement (By.xpath (MailPage.PASSWORD_NEXT)));
    }

    @Test(priority = 2)
    public void sendLetter() {
        try {
            text = sendMassege ();
        } catch (IOException e) {
            e.printStackTrace ();
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        driver.findElement (By.xpath (MailPage.WRITE_LETTER)).click ();
        driver.findElement (By.className (MailPage.MAILING_ADDRESS)).sendKeys (Variables.MAILINGADDRESS);
        driver.findElement (By.className (MailPage.TOPIC)).sendKeys (topic ());
        driver.findElement (By.xpath (MailPage.TEXT)).sendKeys (text);
        driver.findElement (By.cssSelector (MailPage.SEND)).click ();
    }

    @Test(priority = 3)
    public void check() {
        String selectAll = Keys.chord (Keys.CONTROL, "F5");
        driver.findElement (By.tagName ("html")).
                sendKeys (selectAll);
        WebElement ele = driver.findElement (By.xpath (MailPage.INBOX));
        List<WebElement> elements = ele.findElements (By.cssSelector ("tr"));
        for (WebElement element : elements) {
            if (element.getText ().contains (Variables.TOPIC)) {
                element.click ();
            }
        }
        List<WebElement> ele1 = driver.findElements (By.xpath (MailPage.TEXT_LETTER));
        for (WebElement element : ele1) {
            letter += element.getText ();
        }
        System.out.println (letter.contains (text));
        System.out.println (text.contains (letter));
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
        driver.close ();
    }
}
