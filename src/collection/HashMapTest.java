package collection;

import beans.Employee;
import org.junit.Test;

import java.util.*;

/**
 * @Author Tom
 * @Date 2020/12/1 15:03
 * @Version 1.0
 * @Description
 */
public class HashMapTest {
    //测试1：自定义对象做为key时，不重写equals和hashcode方法的结果
    @Test
    public void test1() {
        //hashmap中,key是employee,value是奖金
        HashMap<Employee, Integer> employeeBonus = new HashMap<>();
        employeeBonus.put(new Employee(1001, "tom", 40, 1000), 10000);
        employeeBonus.put(new Employee(1002, "peter", 40, 1000), 20000);
        employeeBonus.put(new Employee(1003, "while", 40, 1000), 30000);

        for (Map.Entry<Employee, Integer> employeeIntegerEntry : employeeBonus.entrySet()) {
            System.out.println(employeeIntegerEntry.getKey().getName() + "奖金 -> " +
                    employeeIntegerEntry.getValue());
        }
        //如果equals和hashcode方法有一个不重写（必须两个都重写），结果就是这样的，也就是有两个重复的记录。
        //修改Tom的奖金到100万
        //tom奖金 -> 1000000
        //tom奖金 -> 10000
        //peter奖金 -> 20000
        //while奖金 -> 30000
        System.out.println("修改Tom的奖金到100万");
        employeeBonus.put(new Employee(1001, "tom", 40, 1000), 1000000);
        for (Map.Entry<Employee, Integer> employeeIntegerEntry : employeeBonus.entrySet()) {
            System.out.println(employeeIntegerEntry.getKey().getName() + "奖金 -> " +
                    employeeIntegerEntry.getValue());
        }

    }

    //测试2：遍历hashmap的几种方式比较，其中第一种效率最低，慎用！！！！

    //构造一个大的hashMap
    static HashMap<Integer, String> hashMap = new HashMap<>();

    static {
        for (int i = 0; i < 10000000; i++) {
            hashMap.put(i, "name" + i);
        }
    }

    //1） 第一种遍历方式
    @Test
    public void test2() {
        String temp;
        long start = System.currentTimeMillis();
        System.out.println("start....");
        for (Integer integer : hashMap.keySet()) {
//            System.out.println("id: " + integer + " -> " + "name: " + hashMap.get(integer));
            temp = hashMap.get(integer);
        }

        long end = System.currentTimeMillis();
        System.out.println("end....");
        System.out.println("Test2 Time consumed: " + (end - start));
    }

    //2) 第二种遍历方式
    //测试结果：好像跟第一种差不多，没有感觉第一种慢多少....
    @Test
    public void test3(){
        String temp;
        long start = System.currentTimeMillis();
        ArrayList<String> list = new ArrayList<String>();
        
        
//        for (Map.Entry<Integer, String> integerStringEntry : hashMap.entrySet()) {
////            System.out.println("id: " + integerStringEntry.getKey() + " -> " + "name: " + integerStringEntry.getValue());
//            temp = integerStringEntry.getValue();
//        }
        long end = System.currentTimeMillis();
        System.out.println("Test3 Time consumed: " + (end - start));
    }

    //3） 第三种遍历方式
    @Test
    public void test4(){
        String temp;
//        System.out.println(String.format("开始时间: %s", new Date()));
        long start = System.currentTimeMillis();
        Collection<String> values = hashMap.values();
        for (String value : values) {
            temp = value;
        }
//        System.out.println(String.format("结束时间: %s", new Date()));
        long end = System.currentTimeMillis();
        System.out.printf("Time consumed: %d", (end - start));
    }
}
