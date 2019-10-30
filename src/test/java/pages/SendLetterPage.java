package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SendLetterPage {
    WebDriver driver;


    By writeLetter = By.xpath (MailPage.WRITE_LETTER);
    By mailingAddress = By.className (MailPage.MAILING_ADDRESS);
    By topic = By.className (MailPage.TOPIC);
    By bodyLetter = By.xpath (MailPage.TEXT);
    By send = By.cssSelector (MailPage.SEND);

    public SendLetterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void letter(String address, String top, String textletter) {

        driver.findElement (writeLetter).click ();
        driver.findElement (mailingAddress).sendKeys (address);
        driver.findElement (topic).sendKeys (top);
        driver.findElement (bodyLetter).sendKeys (textletter);
        driver.findElement (send).click ();
    }
}

