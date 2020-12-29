package multi_thread.basic.basic.thread_state;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author Tom
 * @Date 2020/6/2 10:57
 * @Version 1.0
 * @Description :测试jconsole上对locksupport.lock()线程的显示。注意这句话根object.wait()中提示
 * 类似 - java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@46419faf上的WAITING
 * 那是不是表示locksupport.lock()是锁住了这个对象的monitor呢，我感觉没有关系。这个就是纯粹表示是哪个对象调用了
 * locksupport.lock()方法，而locksupport.lock()是静态方法，谁都可以调用，后续谁调用
 * locksupport.unlok()去解锁该线程，完全不依赖于AbstractQueuedSynchronizer$ConditionObject对象。
 *
 * 名称: Thread-0
 * 状态: java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@46419faf上的WAITING
 * 总阻止数: 0, 总等待数: 2
 *
 * 堆栈跟踪:
 * sun.misc.Unsafe.park(Native Method)
 * java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
 * java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
 * java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:234)
 * java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:362)
 * multi_thread.basic.basic.thread_state.TestThreadState2.lambda$main$0(TestThreadState2.java:20)
 * multi_thread.basic.basic.thread_state.TestThreadState2$$Lambda$1/1149319664.run(Unknown Source)
 * java.lang.Thread.run(Thread.java:748)
 */
public class TestThreadState2 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is doing something");
                Thread.sleep(1_000);
                System.out.println(Thread.currentThread().getName() + " enter wait");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " enter wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is doing something");
                Thread.sleep(1_000);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        LockSupport.unpark(t1);
    }
}

