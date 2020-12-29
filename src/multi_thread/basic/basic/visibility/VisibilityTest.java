package multi_thread.basic.basic.visibility;

import java.util.concurrent.TimeUnit;

/**
 * @Author Tom
 * @Date 2020/6/17 16:44
 * @Version 1.0
 * @Description
 */
public class VisibilityTest {
    private static boolean ready;
    private static int number;

    static class ReaderThread extends Thread{

        @Override
        public void run() {
            while(true){
                while(!ready){
                    this.yield();
                }
                System.out.println("The result is " + number);
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        for (int i = 0; i < 100; i++) {
            TimeUnit.MILLISECONDS.sleep(30);
            ready = true;
            number = i;
        }
    }
}
