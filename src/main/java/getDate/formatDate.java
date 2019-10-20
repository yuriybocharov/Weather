package getDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class formatDate {

    public String date(String dateInString) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
        Date date = formatter.parse (dateInString);
        String[] st = date.toString ().split (" ");
        String ret = "";
        for (int i = 0; i < st.length; i++) {
            if (i != 3 & i != 4) {
                ret = ret + st[i] + " ";
            }
        }
        return ret;
    }
}
