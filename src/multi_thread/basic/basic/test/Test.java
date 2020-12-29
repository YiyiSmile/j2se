package multi_thread.basic.basic.test;

/**
 * @Author Tom
 * @Date 2020/6/6 20:49
 * @Version 1.0
 * @Description:
 * argument a of function fun1 is also a variable, when call fun1(a) in main function,
 * the value of outer variable a is assigned to inner variable a in fun1.
 * We can compare this with c pointer.
 */
public class Test {
    public static void main(String[] args) {
//        int a = 10;
//        fun1(a);
//        System.out.println("a: " + a);
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }

    public static void fun1(int a){
        System.out.println("The old value: " + a);
        a = a + 1;
        System.out.println("The new value " + a);
    }
}
