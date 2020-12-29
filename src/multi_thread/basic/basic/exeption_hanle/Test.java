package multi_thread.basic.basic.exeption_hanle;

/**
 * @Author Tom
 * @Date 2020/5/28 10:08
 * @Version 1.0
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        int c;
        System.out.println("start 10/0");
        try {
            Thread.sleep(1_000);
            c = a/b;  //抛出ArithmeticException,这个是无法捕获的，后边的print语句无法执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish");
    }
}
