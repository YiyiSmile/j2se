package userfull_class;

import org.junit.Test;

import java.util.Date;

/**
 * @Author Tom
 * @Date 2020/12/14 21:17
 * @Version 1.0
 * @Description
 */
public class DateTest01 {
    //Test Date class
    @Test
    public void test01() throws InterruptedException {
        Date date1 = new Date();
        Thread.sleep(5000);
        Date date2 = new Date(System.currentTimeMillis());
        Date date3 = new Date(2020-1900, 11, 14);
        Date date4 = new Date(2020-1900, 06, 14, 21, 11, 53);
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);

        System.out.println(date1.getTime());
        System.out.println(date1.toString());
    }


}
