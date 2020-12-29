package multi_thread.juc.utils.shared_lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author Tom
 * @Date 2020/6/2 11:52
 * @Version 1.0
 * @Description
 * 个人理解：
 * CyclicBarrier于CountdownLatch非常类似。有些功能用二者中的任何一个都可以实现，比如：
 * 1.一个或多个线程在等待一个互殴多个其他线程完成，再执行，实际上用CyclicBarrier也能实现。
 * 2. 当多个线程共同达到同一个条件，然后一起继续执行，这个用CountdownLatch也能实现
 * 也就是说这两个功能他们都可以实现。但是还是有一些区别的，比如:
 * 1. CyclicBarrier用完一次，可以继续使用
 * 2. CyclicBarrier用完一次，可以reset
 * 3. CyclicBarrier可以设置回调函数，这个回调函数不是要起一个新的线程执行。
 * 当参与CyclicBarrier.await()的所有线程都执行完，
 * 其中随后一个执行的线程使CyclicBarrier达到临界点，那么这个回调函数可以被最后一个线程执行，作为
 * CyclicBarrier.await()的一部分，被CyclicBarrier.await()调用。
 * 看了以下源码，确实是被最后一个线程的await()方法调用。下边的command对象就是回调的runnable对象。
 *
 *   private int dowait(boolean timed, long nanos)
 *         throws InterruptedException, BrokenBarrierException,
 *                TimeoutException {
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *             final Generation g = generation;
 *
 *             if (g.broken)
 *                 throw new BrokenBarrierException();
 *
 *             if (Thread.interrupted()) {
 *                 breakBarrier();
 *                 throw new InterruptedException();
 *             }
 *
 *             int index = --count;
 *             if (index == 0) {  // tripped
 *                 boolean ranAction = false;
 *                 try {
 *                     final Runnable command = barrierCommand; <<<<<<<<<<<<<<<<<
 *                     if (command != null)
 *                         command.run();
 *                     ranAction = true;
 *                     nextGeneration();
 *
 *
 *
 */
public class CycliBarrierExample {
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

    }
}
