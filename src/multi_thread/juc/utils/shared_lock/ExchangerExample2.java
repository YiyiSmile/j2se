package multi_thread.juc.utils.shared_lock;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author Tom
 * @Date 2020/6/2 15:50
 * @Version 1.0
 * @Description
 */
public class ExchangerExample2 {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread() {
            @Override
            public void run() {
                AtomicReference<Integer> atomicReference = new AtomicReference<>(1);
                while (true) {
                    try {
                        Integer receivedValue = exchanger.exchange(atomicReference.get());
                        atomicReference.set(receivedValue);
                        System.out.println("Thread A Integer value is " + atomicReference.get());
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                AtomicReference<Integer> atomicReference = new AtomicReference<>(2);
                while (true) {
                    try {
                        Integer receivedValue = exchanger.exchange(atomicReference.get());
                        atomicReference.set(receivedValue);
                        System.out.println("Thread B Integer value is " + atomicReference.get());
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }
}
