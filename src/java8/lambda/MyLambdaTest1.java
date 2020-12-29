package java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Author Tom
 * @Date 2020/11/20 15:56
 * @Version 1.0
 * @Description
 */
public class MyLambdaTest1 {

    //语法格式一、无参，无返回值
    @Test
    public void test() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I love coding.");
            }
        };
        r1.run();
        System.out.println("###################################");
        Runnable r2 = () -> System.out.println("I love java");
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                return Integer.compare(o1, o2);
                return o1.compareTo(o2);
            }
        };
        int result1 = com1.compare(12, 12);
        System.out.println(result1);

        System.out.println("############################");

        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(12, 11));

    }

    //语法格式二、一个参数，无返回值
    @Test
    public void test3() {
/*        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };


        consumer.accept("I love China");*/

        class MyConsumer implements Consumer<int[]> {

            @Override
            public void accept(int[] ints) {
                if (ints.length > 0) {
                    for (int anInt : ints) {
                        System.out.println(anInt);
                    }
                }
            }
        }
        int[] a = {1, 2, 3};

        Consumer<int[]> consumer = new MyConsumer();


        System.out.println("hell".toString());
        System.out.println("#################################");
        Consumer<String> consumer2 = (String s) -> System.out.println(s);
        consumer2.accept("I love lambda");
    }

    @Test
    public void test4() {
        happyTime(100, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("It's too tired to study, I went to supermaket adn buy a" +
                        " bottle of water, the price is:" + aDouble);
            }
        });
        System.out.println("###########################");
        happyTime(200, p -> System.out.println("学习太累了，去天上人间买了瓶水，花了:"
                + p));
    }

    public void happyTime(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test5(){
        List<String> list = Arrays.asList("北京","南京","天津","东京","西京");
        List<String> filterStrings = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterStrings);
        List<String> list2 = filterString(list, s -> s.contains("京"));
        System.out.println(list2);
    }
    public List<String> filterString(List<String> list, Predicate<String> pre){
        List<String> filterList = new ArrayList<>();
        for (String s : list) {
            if(pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }
}
