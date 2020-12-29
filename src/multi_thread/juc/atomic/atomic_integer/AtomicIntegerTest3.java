package multi_thread.juc.atomic.atomic_integer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Tom
 * @Date 2020/5/30 14:24
 * @Version 1.0
 * @Description
 * Use synchronized, compare the perfomance with using AtomicInteger, no significant difference.
 *
 */
public class AtomicIntegerTest3 {

    private static int value = 0;
     private static synchronized void increaseOne(){
        value ++;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 500000) {
                    increaseOne();
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 500000) {
                    increaseOne();
//                    set.add(temp);
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 500000) {
                    increaseOne();
//                    set.add(temp);
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                }
            }
        };
        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " consumed.");

    }
}
