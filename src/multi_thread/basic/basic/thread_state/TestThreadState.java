package multi_thread.basic.basic.thread_state;

import java.util.concurrent.TimeUnit;

/**
 * @Author Tom
 * @Date 2020/6/2 10:57
 * @Version 1.0
 * @Description
 * :测试jconsole上对object.wait()线程的显示。等下与locksupport.part()方法的对比，是不是也是
 * 提示： multi_thread.basic.basic.thread_state.Task@6d969a52上的WAITING
 * 这句话不是表示Task对象调用了wait()方法，而是表示这个wait是在等待Task对象上的某种操作。比如下边第二个测试
 * 如果是调用第三方对象的wait()，他就说multi_thread.basic.basic.thread_state.Lock@28fdb828上的WAITING。

 *
 * 1. Task使用this锁，线程输出
 * 名称: Thread-0
 * 状态: multi_thread.basic.basic.thread_state.Task@6d969a52上的WAITING
 * 总阻止数: 0, 总等待数: 2
 *
 * 堆栈跟踪:
 * java.lang.Object.wait(Native Method)
 * java.lang.Object.wait(Object.java:502)
 * multi_thread.basic.basic.thread_state.Task.run(TestThreadState.java:28)
 * java.lang.Thread.run(Thread.java:748)
 *
 * 2. Task2使用第三方对象锁，线程输出
 * 名称: Thread-1
 * 状态: multi_thread.basic.basic.thread_state.Lock@28fdb828上的WAITING
 * 总阻止数: 1, 总等待数: 2
 *
 * 堆栈跟踪:
 * java.lang.Object.wait(Native Method)
 * java.lang.Object.wait(Object.java:502)
 * multi_thread.basic.basic.thread_state.Task2.run(TestThreadState.java:64)
 * java.lang.Thread.run(Thread.java:748)
 *
 *
 */
public class TestThreadState {
    public static void main(String[] args) {
        Task2 task = new Task2();
        new Thread(task).start();
        new Thread(task).start();

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        task.notifyAll();
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        //lock itself.
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + " is doing something.");
                Thread.sleep(1_000);
                System.out.println(Thread.currentThread().getName() + " enter wait.");
                this.wait();
                System.out.println(Thread.currentThread().getName() + "exit wait.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
    class Task2 implements Runnable {
        private Lock lock = new Lock();

        @Override
        public void run() {
            //lock itself.
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is doing something.");
                    Thread.sleep(1_000);
                    System.out.println(Thread.currentThread().getName() + " enter wait.");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + "exit wait.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
class Lock{};

