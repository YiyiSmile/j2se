package junit;

import junit.framework.Assert;
import org.junit.Test;

import java.io.ObjectStreamException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Tom
 * @Date 2020/12/8 9:03
 * @Version 1.0
 * @Description
 */
public class Test01 {



    @Test
    public void test01(){

        System.out.println("test1");
        String aa = "hello world";
        Assert.assertEquals(true, aa.contains("hello"));
        System.out.println("hello");
        Assert.assertEquals(true, aa.contains("tom"));
    }
    @Test
    public void test02(){
        System.out.println("test2");
        String aa = "hello world";
        Assert.assertEquals(true, aa.contains("hello"));
        System.out.println("hello");
        Assert.assertEquals(true, aa.contains("tom"));
    }

    @Test
    public void test03(){
        String s = "hello world";
        Class clazzFromObject = s.getClass();
        Class clazzFromClass = String.class;
        Assert.assertSame(clazzFromClass, clazzFromObject);

        List<String> fruits = Arrays.asList("Apple", "Grapes", "Strawberries");
        List<String> fruits2 = Arrays.asList("Apple", "Grapes", "Strawberries");
        fruits.equals(fruits2);

    }
    @Test
    public void test04(){
        String s1 = new String("a")+ new String("b");
        String s2 = "ab";
        String s3 = new StringBuilder("a").append("b").toString();
        System.out.println(s1.intern() == s1);//true
        System.out.println(s1.intern() == s2);
        System.out.println(s3 == s2);
//        String s1 = new String("a")+ new String("b");
//        System.out.println(s1.intern() == s1);//true
    }
}
