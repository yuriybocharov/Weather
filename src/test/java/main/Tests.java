package main;


import org.testng.annotations.*;
import pages.*;
import pages.Variables;
import getDate.FormatDate;
import tools.WeatherCity;

import java.io.IOException;
import java.text.ParseException;

import static tools.CreatMassege.sendMassege;


public class Tests extends BaseWebDriver {

    String text = null;
    String textLetter = "";

    public static final String topic() {
        return Variables.TOPIC += WeatherCity.CYTI + " " + FormatDate.time;
    }


    @Test(priority = 1, description = "goes to the mail")
    public void login() throws InterruptedException {
        LoginPage loginPage = new LoginPage (driver, wait);
        loginPage.loginToGmail (Variables.LOGIN, Variables.PASSWORD);
    }

    @Test(priority = 2, dependsOnMethods = "login", description = "sends a letter describing the weather")
    public void sendLetter() throws Exception {
        try {
            text = sendMassege ();
        } catch (IOException e) {
            e.printStackTrace ();
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        SendLetterPage sendLetterPage = new SendLetterPage (driver);
        sendLetterPage.letter (Variables.MAILINGADDRESS, topic (), text);

    }

    @Test(priority = 3, dependsOnMethods = "sendLetter", description = "in the mail we go to the Inbox folder we " +
            "find the letter by the header and check the text of the letter so that it was sent")
    public void check() throws Exception {
        CheckLetter checkLetter = new CheckLetter (driver);
        checkLetter.letterSearch ();
        textLetter = checkLetter.textLetter ();

        System.out.println (textLetter.contains (text));
        System.out.println (text.contains (textLetter));
        //System.out.println (textLetter);

    }

}
