package acmr.springframework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static final SimpleDateFormat FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
    public static final int DAY_TIME = 24 * 60 * 60 * 1000;

    public static Date strToDatetime(String date) throws ParseException {
        return FORMAT_DATETIME.parse(date);
    }

    public static Date strToDate(String date) throws ParseException {
        return FORMAT_DATE.parse(date);
    }

    public static long getTimeStamp(String date) {
        try {
            return System.currentTimeMillis() - FORMAT_DATE.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据出生年月获取年龄
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        if(birthday == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currYear = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int birthYear = calendar.get(Calendar.YEAR);
        return currYear - birthYear;
    }
}
