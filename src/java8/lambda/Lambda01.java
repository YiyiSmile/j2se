package java8.lambda;


import org.junit.Test;

import java.net.InterfaceAddress;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Lambda表达式的使用
 *
 * 1.举例： (o1,o2) -> Integer.compare(o1,o2);
 * 2.格式：
 *      -> :lambda操作符 或 箭头操作符
 *      ->左边：lambda形参列表 （其实就是接口中的抽象方法的形参列表）
 *      ->右边：lambda体 （其实就是重写的抽象方法的方法体）
 *
 * 3. Lambda表达式的使用：（分为6种情况介绍）
 *
 *    总结：
 *    ->左边：lambda形参列表的参数类型可以省略(类型推断)；如果lambda形参列表只有一个参数，其一对()也可以省略
 *    ->右边：lambda体应该使用一对{}包裹；如果lambda体只有一条执行语句（可能是return语句），省略这一对{}和return关键字
 *
 * 4.Lambda表达式的本质：作为函数式接口的实例
 *
 * 5. 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口。我们可以在一个接口上使用 @FunctionalInterface 注解，
 *   这样做可以检查它是否是一个函数式接口。
 *
 * 6. 所以以前用匿名实现类表示的现在都可以用Lambda表达式来写。
 *
 * @author shkstart
 * @create 2019 上午 11:40
 */
public class Lambda01 {

    @Test
    public void test1(){
        MyInterface my1 = new MyInterface() {
            @Override
            public void displayName(String name) {
                System.out.println(name);
            }
        };
        my1.displayName("tom");
        System.out.println("******************");
        MyInterface my2 = (name) -> System.out.println(name);
        MyInterface my3 = System.out::println;
        my2.displayName("Dale");
        Consumer<String> consumer;
        Comparator<Integer> comparator;

        Comparable comparable;
    }

    @FunctionalInterface
    public interface MyInterface{
        void displayName(String name);


    }

    @Test
    public void test2(){
        Parent1.run();
    }

    @Test
    public void test3(){
        /*Function<String, String> function = new Function<String, String>() {

            @Override
            public String apply(String s) {
                return "hello" + s;
            }
        };*/
        Function<String,String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "hello " + s;
            }
        };
        System.out.println(function.apply("tom"));
        System.out.println("***********************");
        Function<String,String> function1 = t -> "hello " + t;
        System.out.println(function1.apply("Eva"));
    }

    @Test
    public void test4(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello " + s);
            }
        };
        consumer.accept("tom");

        Consumer<String> consumer1 = t -> System.out.println("Hello " + t);
        consumer1.accept("Eva");
    }



}

interface Parent1{
    public static void run(){
        System.out.println("running");
    }
}

class Child implements Parent1 {}
