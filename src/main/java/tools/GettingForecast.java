package tools;


import getDate.FormatDate;
import model.WeatherData;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import static tools.JsonReader.readJsonFromUrl;

public class GettingForecast {

    static ArrayList<Day> listdays = new ArrayList ();
    static ArrayList<Double> tempOneDay = new ArrayList ();
    static ArrayList<Double> tempOneNight = new ArrayList ();

    public static ArrayList<Day> weather() throws IOException, ParseException {
        WeatherData weatherData = readJsonFromUrl ("https://api.openweathermap.org/data/2.5/forecast?q="
                + WeatherCity.CYTI + "&units=metric&appid=811868fedd9d7eaa4cb55dc3622a535b");
        FormatDate mb = new FormatDate ();

        String startDay = mb.date (weatherData.getList ().get (0).getDtTxt ());



        for (int i = 0; i < weatherData.getList ().size (); i++) {

            if ((weatherData.getList ().get (i).getSys ().getPod ()).equals ("d")) {
                tempOneDay.add (weatherData.getList ().get (i).getMain ().getTemp ());
            }

            if ((weatherData.getList ().get (i).getSys ().getPod ()).equals ("n")) {
                tempOneNight.add (weatherData.getList ().get (i).getMain ().getTemp ());
            }
            tempOneDay.size ();

            if (!startDay.equals (mb.date (weatherData.getList ().get (i).getDtTxt ()))) {
                if (tempOneDay.isEmpty ()) {
                    tempOneDay.add (0.00);
                }
                if (tempOneNight.isEmpty ()) {
                    tempOneNight.add (0.00);
                }
                listdays.add (new Day (mb.date (weatherData.getList ().get (i).getDtTxt ()), Collections.min (tempOneDay), Collections.max (tempOneDay), Collections.min (tempOneNight), Collections.max (tempOneNight)));
                startDay = mb.date (weatherData.getList ().get (i).getDtTxt ());
                tempOneNight = new ArrayList<> ();
                tempOneDay = new ArrayList<> ();
            }
        }
        return listdays;
    }
}
