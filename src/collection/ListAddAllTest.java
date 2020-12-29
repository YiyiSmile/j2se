package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Tom
 * @Date 2020/12/6 17:47
 * @Version 1.0
 * @Description
 */
public class ListAddAllTest {

    //Test 1. Test with ArrayList has no problem
    @Test
    public void test1(){
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(11);
        list1.add(12);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(13);
        list2.add(14);
        list2.add(15);

        list2.addAll(list1);
        System.out.println(list2);
    }

    //Test 2: 使用Arrays.asList()返回的list测试，发现不行.这是因为Arrays.asList()返回的list不能往里添加任何数据
    //Arrays.asList()返回的是一个内部定义的ArrayLRist，继承自AbstractList，而AbstractList的add()返回直接抛出java.lang.UnsupportedOperationException

    @Test
    public void test2(){
        List<Integer> list1 = Arrays.asList(10, 11, 12);
        List<Integer> list2 = Arrays.asList(13, 14, 15);

//        list2.addAll(list1);
        System.out.println("hello world");
    }
}
