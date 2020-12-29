package userfull_class;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Tom
 * @Date 2020/12/14 21:26
 * @Version 1.0
 * @Description
 */
public class SimpleDateFormatTest01 {

    @Test
    public void test1() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String stringDate = sdf.format(date);
        System.out.println(stringDate);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy 年 MM 月 dd 日 EEE HH:mm:ss");
        System.out.println(sdf2.format(date));

        Date date1 = sdf2.parse("2020 年 12 月 16 日 星期三 2:30:46");
        System.out.println(date1);
    }

    @Test
    public void test2() throws ParseException {
        String sDate = "2009-10-28";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(sDate);
        System.out.println(date);
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        System.out.println(date1);
    }
}
