package multi_thread.juc.utils.shared_lock;

import java.util.concurrent.CountDownLatch;

/**
 * @Author Tom
 * @Date 2020/5/31 8:15
 * @Version 1.0
 * @Description
 * demonstrate two threads wait for one thread to finish.
 *
 *
 */
public class CountDownLatchExample2 {
    public static void main(String[] args) {
        //1. Thread 1 do some initial work, then wait until thread 2 finishes. thread 3 is
        //   similar to thread 1 - waiting thread2 to finish preparing data.
        //2. Thread 2 is preparing data for thread 1 at the same time.
        //3. Once thread 2 finishes the work, thread 1 continue to handle the data.
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is doing some " +
                        "initial work");
                try {
                    Thread.sleep(2_000);
                    System.out.println(Thread.currentThread().getName() + " finished its initial work, starting to wait" +
                            "the other thread to finish preparing the data.");
                    latch.await();
                    System.out.println(Thread.currentThread().getName() + " stop waiting, continue his work.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is simultaneously preparing data for the " +
                        "other thread.");
                try {
                    Thread.sleep(4_000);
                    System.out.println(Thread.currentThread().getName() + " finish preparing the data.");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " finished its initial work, starting to wait" +
                            "the other thread to finish preparing the data.");
                    latch.await();
                    System.out.println(Thread.currentThread().getName() + " stop waiting, continue his work.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
