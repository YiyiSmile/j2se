package multi_thread.juc.utils.shared_lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tom
 * @Date 2020/6/2 17:47
 * @Version 1.0
 * @Description:
 * Semaphore usage scenario: The connection pool.
 * Acquire will fail when no available connection in pool.
 */
public class SemaphoreExample1 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " in");
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " acquire one permit.");
                        System.out.println(Thread.currentThread().getName() + " is doing something");
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName() + " out");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                }
            }.start();

        }
        while(true){
            System.out.println("The current available permits are: " + semaphore.availablePermits());
            System.out.println("The current estimated blocked threads number are: " + semaphore.getQueueLength());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
