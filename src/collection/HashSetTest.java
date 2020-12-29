package collection;

import beans.Employee;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @Author Tom
 * @Date 2020/12/1 15:10
 * @Version 1.0
 * @Description
 */
public class HashSetTest {
    //hashset中的元素一定要重写hashcode和equals方法
    @Test
    public void test(){
        HashSet<Employee> employeesSet = new HashSet<>();
        employeesSet.add(new Employee(1001, "tom", 40, 1000));
        employeesSet.add(new Employee(1001, "tom", 40, 1000));
        employeesSet.add(new Employee(1001, "tom", 40, 1000));
        employeesSet.add(new Employee(1001, "tom", 40, 1000));

        System.out.println(employeesSet);
        System.out.println(employeesSet.size());
        //不重写equals和hashcode方法：
        // [Employee{id=1001, name='tom', age=40, salary=1000.0}, Employee{id=1001, name='tom', age=40, salary=1000.0}, Employee{id=1001, name='tom', age=40, salary=1000.0}, Employee{id=1001, name='tom', age=40, salary=1000.0}]
        //4
        //只重写equals方法，结果根上边一样
        //只重写hashcode方法，结果根上边一样
        //equals和hashcode方法都重写，结果：
        // [Employee{id=1001, name='tom', age=40, salary=1000.0}]
        //1


    }
    //hashset三种遍历方式
    @Test
    public void test2(){
        HashSet<String> strings = new HashSet<>();
        strings.add("tom");
        strings.add("peter");
        strings.add("white");
        strings.add("green");
        System.out.println("迭代器遍历");
        for (Iterator iterator = strings.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
        System.out.println("增强for循环遍历");
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println("foreach遍历");
        strings.forEach(System.out::println);
    }
}
