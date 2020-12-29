package jvm;

/**
 * @Author Tom
 * @Date 2020/12/6 21:08
 * @Version 1.0
 * @Description
 */
public class StackFrameTest01 {
    public static void main(String[] args) {
        new StackFrameTest01().method1();
    }
    private void method1(){
        System.out.println("method1 开始执行...");
        method2();
        System.out.println("method1 退出...");
    }
    private void method2(){
        System.out.println("method2 开始执行...");
        method3();
        System.out.println("method2 退出...");
    }
    private void method3(){
        System.out.println("method3 开始执行...");
        System.out.println("method3 退出...");
    }

}
