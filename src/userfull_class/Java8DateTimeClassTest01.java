package userfull_class;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author Tom
 * @Date 2020/12/15 10:23
 * @Version 1.0
 * @Description
 */
public class Java8DateTimeClassTest01 {
    //Traditional Date/Calendar
    @Test
    public void test01() {
        Date date = new Date(2020 - 1900, 7 - 1, 10);
        Date date1 = new Date(2020 - 1900, 6 - 1, 6, 7, 8, 9);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 7 - 1, 10, 11, 12, 13);
        System.out.println(date);
        System.out.println(date1);
        System.out.println(calendar.getTime());
    }

    //New Date related Class introduced in Java 8
    @Test
    public void test02() {
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(time);
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 11, 12, 13, 14, 15);
        System.out.println(localDateTime1);

        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getDayOfWeek());
        System.out.println(localDateTime1.getMonth());
        System.out.println(localDateTime1.getMonthValue());
        System.out.println(localDateTime1.getMinute());

        //reflect the immutability
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);
        System.out.println(localDate1);

        LocalDateTime localDateTime2 = localDateTime1.withHour(12);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime1.plusMonths(1);
        System.out.println(localDateTime3);


    }

    //Usage of Instant class, it is a bit similar to java.Util.Date
    @Test
    public void test03() {
        //UTC + 0 time
        Instant instant = Instant.now();
        System.out.println(instant); //2020-12-15T02:53:10.734Z

        //UTC + 8
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2020-12-15T10:53:10.734+08:00

        //similar to Date.getTime(), milliseconds since 1970/1/1 00:00:00
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        Instant instant1 = Instant.ofEpochMilli(1550475314878L);
        System.out.println(instant1);
    }

    /*
     * DateTimeFormatter: format date and time, similar to SimpleDateFormat
     * */
    @Test
    public void test04() {

        //Method 1: Pre-defined format: ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        //format: datetime -> string
        String sLocalDateTime = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(sLocalDateTime);

        //parse: string -> datetime
        TemporalAccessor parse = formatter.parse("2019-04-11T15:42:18.797");
        System.out.println(parse);

        //method 2: local related format. for example:
        //      ofLocalizedDateTime()
//        FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT :适用于LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String sLocalDateTime1 = formatter1.format(localDateTime);
        System.out.println(sLocalDateTime1);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String sLocalDate = formatter2.format(LocalDate.now());
        System.out.println(sLocalDate);

        //重点：customized format. for example: ofPattern(“yyyy-MM-dd hh:mm:ss”)
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String sLocalDatetime3 = formatter3.format(LocalDateTime.now());
        System.out.println(sLocalDatetime3);

        //parse with customized format string.
        TemporalAccessor localDateTime4 = formatter3.parse("2020-11-15 11:19:04");
        System.out.println(localDateTime4);


    }
}
