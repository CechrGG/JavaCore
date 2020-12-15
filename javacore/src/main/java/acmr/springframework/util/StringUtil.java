package acmr.springframework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    private static final SimpleDateFormat FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");

    public static Date strToDatetime(String date) throws ParseException {
        return FORMAT_DATETIME.parse(date);
    }

    public static Date strToDate(String date) throws ParseException {
        return FORMAT_DATE.parse(date);
    }
}
