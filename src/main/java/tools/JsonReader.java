package tools;

import com.google.gson.Gson;
import getDate.FormatDate;
import model.WeatherData;
import org.json.JSONException;


import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;


public class JsonReader {


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder ();
        int cp;
        while ((cp = rd.read ()) != -1) {
            sb.append ((char) cp);
        }
        return sb.toString ();
    }

    public static WeatherData readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL (url).openStream ();
        try {
            BufferedReader rd = new BufferedReader (new InputStreamReader (is, Charset.forName ("UTF-8")));
            String stringFromUrl = readAll (rd);
            WeatherData dataFromSite = new Gson ().fromJson (stringFromUrl, WeatherData.class);
            return dataFromSite;
        } finally {
            is.close ();
        }
    }

    public static ArrayList<Day> weather() throws IOException, ParseException {
        WeatherData weatherData = readJsonFromUrl ("https://api.openweathermap.org/data/2.5/forecast?q="
                + WeatherCity.CYTI + "&units=metric&appid=811868fedd9d7eaa4cb55dc3622a535b");
        FormatDate mb = new FormatDate ();
        ArrayList<Day> listdays = new ArrayList ();
        String startDay = mb.date (weatherData.getList ().get (0).getDtTxt ());

        ArrayList<Double> tempOneDay = new ArrayList ();
        ArrayList<Double> tempOneNight = new ArrayList ();

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

    public static String sendMassege() throws IOException, ParseException {

        String text = "";
        for (Object listday : weather ()) {

            text += listday.toString () + "\n";
        }
        return text.substring (0, text.length () - 1);
    }

}
