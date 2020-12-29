package userfull_class;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author Tom
 * @Date 2020/12/14 21:40
 * @Version 1.0
 * @Description
 */
public class CalenderTest01 {

    //Calendar can be set to a specific time，Calender.getTime() always return that specific time.
    // If no specific time is set, then
    //Calender.getTime() always return the current time.
    @Test
    public void test01(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        System.out.println(date);
        System.out.println(date.getTime());
        Date date1 = new Date(1607953943272L);
        calendar.setTime(date1);
        System.out.println(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.HOUR, 2);
        calendar.add(Calendar.MONTH, -2);
        System.out.println(calendar.getTime());

    }

    @Test
    public void test02(){
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());

        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        calendar.set(Calendar.DAY_OF_MONTH, 22);
        System.out.println(calendar.getTime());
    }
}
