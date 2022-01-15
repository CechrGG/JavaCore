package acmr.springframework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author GuoGuo
 * @version 1.0
 * @date 2022/01/15
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return 空返回true,非空返回false
     */
    public static boolean isEmpty(String s) {
        return null == s || "".equals(s.trim()) || "null".equals(s.trim()) || "undefined".equals(s.trim());
    }

    public static final SimpleDateFormat FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
    public static final int DAY_TIME = 24 * 60 * 60 * 1000;
    public static final int MOBILE_LENGTH = 11;
    public static final char[] MOBILE_POOL= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static Date strToDatetime(String date) throws ParseException {
        return FORMAT_DATETIME.parse(date);
    }

    public static Date strToDate(String date) throws ParseException {
        return FORMAT_DATE.parse(date);
    }

    /**
     * 生成某个日期开始的时间戳
     * @param date
     * @return
     */
    public static long getTimeStamp(String date) {
        try {
            return System.currentTimeMillis() - FORMAT_DATE.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 随机生成手机号码
     * @return
     */
    public static String getMobile() {
        StringBuilder mobile = new StringBuilder("1");
        Random random = new Random();
        for(int i = 0; i < MOBILE_LENGTH - 1; i++) {
            mobile.append(MOBILE_POOL[random.nextInt(MOBILE_POOL.length)]);
        }
        return mobile.toString();
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
