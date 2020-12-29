package multi_thread.basic.basic.exeption_hanle;

/**
 * @Author Tom
 * @Date 2020/5/30 10:57
 * @Version 1.0
 * @Description
 * 1. run() method
 */
public class CatchThreadUnCheckedRuntimeException {
    private static int A = 10;
    private static int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5_000);
                int result = A/B;
                System.out.println("10/0 result is " + result);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
//        t.setUncaughtExceptionHandler((thread,e) ->{
//            //e.printStackTrace();
//            System.out.println(e);
//            System.out.println(thread.getName());
//        });
        t.start();
    }
}
