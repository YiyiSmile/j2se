package userfull_class;

import org.junit.Test;

/**
 * @Author Tom
 * @Date 2020/12/14 21:00
 * @Version 1.0
 * @Description
 */
public class StringBufferTest01 {

    //test string buffer append null string
    @Test
    public void test(){
        String str = null;
        StringBuffer sb = new StringBuffer(10);
        System.out.println("sb length: " + sb.length());

        sb.append(str);

        System.out.println("sb length: " + sb.length());
        System.out.println("sb content: " + sb);
    }
}
