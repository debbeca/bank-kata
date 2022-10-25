package kata.bankaccount.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateProvider {

    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

    public static Date dateFrom(String dateString) throws ParseException {
        return formatter.parse(dateString);

    }
    public static String printDate(Date date) {
        return formatter.format(date);

    }


}
