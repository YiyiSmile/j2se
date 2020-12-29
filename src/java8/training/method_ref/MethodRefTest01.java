package java8.training.method_ref;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Author Tom
 * @Date 2020/12/7 14:28
 * @Version 1.0
 * @Description
 */
public class MethodRefTest01 {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("hello world");
    }

    @Test
    public void test01(){
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("Hello lambda");
    }

    @Test
    public void test02(){
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello method reference");
    }

    @Test
    public void test03(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int result = comparator.compare(10, 11);//type cast
        System.out.println(result);
    }

    @Test
    public void test04(){
        Comparator<Integer> comparator1 = Integer::compare;
        int result = comparator1.compare(11, 11);//type cast
        System.out.println(result);
    }
}
