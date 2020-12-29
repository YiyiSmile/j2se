package multi_thread.juc.atomic.atomic_integer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Tom
 * @Date 2020/5/30 14:24
 * @Version 1.0
 * @Description
 * use volatile on a shared variable, but still can't guarantee the atomicity.
 */
public class AtomicIntegerTest1 {

    private static volatile int value = 0;

    public static void main(String[] args) throws InterruptedException {

        Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

        //Don't use thread safe atomic class, the result might not be 150000.
        //Although volatile key word is used on variable "value", but volatile can't gurrantee the atomicity.
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 50000) {
//                    set.add(value);
                    value++;
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 50000) {
//                    set.add(value);
                    value++;
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 50000) {
//                    set.add(value);
                    value++;
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(set.size());

    }
}
