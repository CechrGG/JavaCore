package acmr.springframework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return 空返回true,非空返回false
     */
    public static boolean isEmpty(String s) {
        return null == s || s.trim().equals("") || s.trim().equals("null") || s.trim().equals("undefined");
    }

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
