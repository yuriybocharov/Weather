package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckLetter {
    WebDriver driver;
    String letter = "";

    By inbox = By.xpath (MailPage.INBOX);
    By letters = By.cssSelector ("tr");
    By getTextLetter = By.xpath (MailPage.TEXT_LETTER);

    public CheckLetter(WebDriver driver) {
        this.driver = driver;
    }

    public void letterSearch (){
        String selectAll = Keys.chord (Keys.CONTROL, "F5");
        driver.findElement (By.tagName ("html")).
                sendKeys (selectAll);
        WebElement ele = driver.findElement (inbox);
        List<WebElement> elements = ele.findElements (letters);
        for (WebElement element : elements) {
            if (element.getText ().contains (Variables.TOPIC)) {
                element.click ();
            }
        }
    }

    public String textLetter (){
        List<WebElement> ele1 = driver.findElements (getTextLetter);

        for (WebElement element : ele1) {
            letter += element.getText ();
        }
        return letter;
    }
}
