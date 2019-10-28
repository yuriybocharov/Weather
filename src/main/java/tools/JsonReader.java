package tools;

import com.google.gson.Gson;
import model.WeatherData;
import org.json.JSONException;


import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

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

}
