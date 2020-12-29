package java8.training.streamapi.test;


import beans.Employee;
import java8.training.streamapi.util.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 * @author shkstart
 * @create 2019 下午 4:42
 */
public class StreamAPIMiddleOperationTest {

    //1-筛选与切片
    @Test
    public void test1(){

//        filter(Predicate p)——接收 Lambda ， 从流中排除某些元素。
        List<Employee> list = EmployeeData.createEmployees();
        Stream<Employee> stream = list.stream();


        //练习：查询员工表中薪资大于7000的员工信息
        stream.filter(s -> s.getSalary() > 3000).forEach(System.out::println);

        System.out.println("********************");
//        limit(n)——截断流，使其元素不超过给定数量。
        //必须重新生成一个新的strea,如果用上边的会报java.lang.IllegalStateException: stream has already been operated upon or closed
        //stream.limit(2).forEach(System.out::println);
        Stream<Employee> stream1 = list.stream();
        stream1.limit(3).forEach(System.out::println);

        System.out.println("******************");
//        skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补

        Stream<Employee> stream2 = list.stream();
        stream2.skip(3).forEach(System.out::println);

        System.out.println("******************");

//        distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素

        list.add(new Employee(1001, "jack", 25, 3500));
        list.add(new Employee(1001, "jack", 25, 3500));
        list.add(new Employee(1001, "jack", 25, 3500));
        list.add(new Employee(1001, "jack", 25, 3500));

        list.stream().distinct().forEach(System.out::println);


        System.out.println("******************");
//        System.out.println(list);
        List<Integer> integers = Arrays.asList(10, 11, 13, 14, 19, 15, 22);
        List<Integer> integers2 = Arrays.asList(20, 21, 23, 14, 19, 15, 22);
//        integers2.addAll(integers);
//        System.out.println(integers2);
        //Arrays.asList返回的是一个内部ArrayList类，继承自AbstractList类,add方法会抛异常。
        integers.add(30);


/*        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(11);
        list1.add(12);

        List<Integer> list2 = new ArrayList<>();
        list2.add(10);
        list2.add(11);
        list2.add(12);

        list2.addAll(list1);*/

    }

    //映射
    @Test
    public void test2(){
//        map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。


//        练习1：获取员工姓名长度大于3的员工的姓名。

        //练习2：

//        flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。

    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的实例




    @Test
    public void test3(){


    }

    //3-排序
    @Test
    public void test4() {
//        sorted()——自然排序

        //抛异常，原因:Employee没有实现Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);


//        sorted(Comparator com)——定制排序
    }

}
