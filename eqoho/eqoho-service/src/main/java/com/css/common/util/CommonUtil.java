package com.css.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class CommonUtil {
    private static final Pattern SPECIAL_CHARACTERS_PATTERN_URL = Pattern.compile("^[一-龥A-Za-z0-9`~!@#$%^&*()+=|{}':;'-_,\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、  ？\n《》]{0,}$");
    private static final Pattern CHARACTERS_PATTERN_URL = Pattern.compile("^[A-Za-z_0-9]+$");
    private static final Pattern NUMBER_PATTERN_URL = Pattern.compile("^[A-Za-z0-9]+$");
    private static final Pattern VERIFICATION_CODE_PATTERN_URL = Pattern.compile("^[0-9]+$");
    private static final Pattern CELLPHONE_PATTERN_URL = Pattern.compile("^(1[3-9]\\d{9}$)");
    private static final Pattern IDCARD_PATTERN_URL = Pattern.compile("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");

    public CommonUtil() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean checkSpecialCharacters(String data) {
        return !SPECIAL_CHARACTERS_PATTERN_URL.matcher(data).find();
    }

    public static Date formatStrDatetime(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            try {
                DateFormat df = new SimpleDateFormat(format);
                return (Date) df.parseObject(date);
            } catch (ParseException var3) {
                return null;
            }
        }
    }

    public static String formatDatetime(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
    }

    public static String formatStrToStr(String date, String format, String conversionFormat) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            try {
                DateFormat df = new SimpleDateFormat(format);
                Date date1 = (Date) df.parseObject(date);
                df = new SimpleDateFormat(conversionFormat);
                return df.format(date1);
            } catch (ParseException var5) {
                return null;
            }
        }
    }

    public static Date formatStrTime(Long time, String format) {
        if (time != null) {
            return null;
        } else {
            try {
                SimpleDateFormat df = new SimpleDateFormat(format);
                String d = df.format(time);
                Date date = df.parse(d);
                return date;
            } catch (ParseException var5) {
                return null;
            }
        }
    }

    public static int compare_date(String DATE1, String DATE2, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            return 0;
        }
    }

    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w <= 0) {
            w = 7;
        }

        return w;
    }

    public static boolean isNextWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(7) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(5, offset1 + 7);
        calendar2.add(5, offset2 + 7);
        String monday = sdf.format(calendar1.getTime());
        String sunday = sdf.format(calendar2.getTime());
        String format = sdf.format(date);

        try {
            Date parse = sdf.parse(format);
            Date mon = sdf.parse(monday);
            Date sun = sdf.parse(sunday);
            return parse.compareTo(mon) >= 0 && sun.compareTo(parse) >= 0;
        } catch (ParseException var13) {
            var13.printStackTrace();
            return false;
        }
    }

    public static String delHtmlTag(String str) {
        String newstr = "";
        newstr = str.replaceAll("<[.[^>]]*>", "").replaceAll("&nbsp;", "");
        newstr = newstr.replaceAll(" ", "");
        return newstr;
    }

    public static boolean isEmptyCollection(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static String getFirstDayOfWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        int dayWeek = cal.get(7);
        cal.add(5, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();
        return sdf.format(mondayDate);
    }

    public static String getLastDayOfWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        int dayWeek = cal.get(7);
        cal.add(5, cal.getFirstDayOfWeek() - dayWeek + 6);
        Date mondayDate = cal.getTime();
        return sdf.format(mondayDate);
    }

    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; ++i) {
                fourRandom = "0" + fourRandom;
            }
        }

        return fourRandom;
    }

    public static String getWeek(Date date) {
        String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(7) - 1;
        if (week_index < 0) {
            week_index = 0;
        }

        return weeks[week_index];
    }

    public static String getCode(int count) {
        StringBuffer sb = new StringBuffer();
        String codes = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();

        for (int i = 0; i < codes.length(); ++i) {
            char c = codes.charAt(random.nextInt(codes.length()));
            if (sb.length() == count) {
                break;
            }

            if (!sb.toString().contains(c + "")) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static boolean checkCharacters(String data) {
        return !CHARACTERS_PATTERN_URL.matcher(data).find();
    }

    public static boolean checkNumber(String data) {
        return !NUMBER_PATTERN_URL.matcher(data).find();
    }

    public static boolean checkVerificationCode(String data) {
        return !VERIFICATION_CODE_PATTERN_URL.matcher(data).find();
    }

    public static boolean checkCellPhone(String data) {
        return !CELLPHONE_PATTERN_URL.matcher(data).find();
    }

    public static boolean checkIDCard(String data) {
        return !IDCARD_PATTERN_URL.matcher(data).find();
    }
}