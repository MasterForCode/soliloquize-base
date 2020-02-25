package top.soliloquize.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author wb
 * @date 2020/2/25
 */
public class DateFormat {
    public static String DATE_FORMAT_NORMAL = "yyyy-MM-dd";

    /**
     * Date对象转LocalDate对象
     *
     * @param date Date对象
     * @return LocalDate对象
     */
    public static LocalDate date2LocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date对象转String
     *
     * @param date   Date对象
     * @param format 转化格式
     * @return String
     */
    public static String date2String(Date date, String format) {
        if (date == null) {
            return "";
        }
        if (format == null) {
            format = DateFormat.DATE_FORMAT_NORMAL;
        }
        return DateFormat.date2LocalDate(date).format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * LocalDate对象转Date对象
     *
     * @param localDate LocalDate对象
     * @return Date对象
     */
    public static Date localDate2Date(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());

        return Date.from(zdt.toInstant());
    }

    /**
     * String对象转Date对象
     *
     * @param value  String对象
     * @param format 转化格式
     * @return Date对象
     */
    public static Date string2Date(String value, String format) {
        if ("".equals(value) || value == null) {
            return null;
        }
        if (format == null) {
            format = DateFormat.DATE_FORMAT_NORMAL;
        }
        try {
            return new SimpleDateFormat(format).parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String对象转LocalDate对象
     *
     * @param value  String对象
     * @param format 转化格式
     * @return LocalDate对象
     */
    public static LocalDate string2LocalDate(String value, String format) {
        if ("".equals(value) || value == null) {
            return null;
        }
        if (format == null) {
            format = DateFormat.DATE_FORMAT_NORMAL;
        }
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(format));
    }
}
