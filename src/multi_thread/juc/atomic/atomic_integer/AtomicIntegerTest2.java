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
 * Use the AtomicInteger to guarantee the atomicity.
 * Meanwhile, print the total time consumed which will be compared with next test AtomicIntegerTest3
 * which uses synchronized.
 */
public class AtomicIntegerTest2 {

    private static AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

        Thread t1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 500000) {
                    int temp = value.getAndIncrement();
//                    set.add(temp);
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
                    int temp = value.getAndIncrement();
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
                    int temp = value.getAndIncrement();
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
