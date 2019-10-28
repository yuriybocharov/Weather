package tools;


import java.io.IOException;
import java.text.ParseException;

import static tools.GettingForecast.weather;

public class CreatMassege {
    public static String sendMassege() throws IOException, ParseException {

        String text = "";
        for (Object listday : weather ()) {

            text += listday.toString () + "\n";
        }
        return text.substring (0, text.length () - 1);
    }
}
