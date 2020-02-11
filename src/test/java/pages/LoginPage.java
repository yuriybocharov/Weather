package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    By username = By.xpath (MailPage.LOGIN);
    By identifierNext = By.xpath (MailPage.IDENTIFIER_NEXT);
    By password = By.xpath (MailPage.PASSWORD);
    By passwordNext = By.xpath (MailPage.PASSWORD_NEXT);

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void loginToGmail(String userid, String pass) {
        driver.findElement (username).sendKeys (userid);
        wait.until (ExpectedConditions.elementToBeClickable (identifierNext));
        ((JavascriptExecutor) driver).executeScript ("arguments[0].click();", driver.findElement (identifierNext));
       // driver.findElement (identifierNext).click ();
        driver.findElement (password).sendKeys (pass);
        wait.until (ExpectedConditions.elementToBeClickable (passwordNext));
        ((JavascriptExecutor) driver).executeScript ("arguments[0].click();", driver.findElement (passwordNext));
    }

}
