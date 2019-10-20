package weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Variables extends JsonReader {


    public static DateFormat sdf = new SimpleDateFormat ("HH:mm:ss");
    public static Date date = new Date ();
    public static final String time = sdf.format (date);


    public static final String URL = "https://mail.google.com/";
    public static final String LOGIN = "testjuniorqaautomation";
    public static final String PASSWORD = "testjunior1";
    public static final String MAILINGADDRESS = "testjuniorqaautomation@gmail.com";
    public static String TOPIC = "Прогноз погоды ";
    public static final String CYTI = "Odessa,UA";


}
