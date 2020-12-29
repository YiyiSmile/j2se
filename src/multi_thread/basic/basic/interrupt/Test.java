package multi_thread.basic.basic.interrupt;

/**
 * @Author Tom
 * @Date 2020/5/28 10:49
 * @Version 1.0
 * @Description
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread("myThread");
        myThread.start();
        System.out.println("main thread start");
        Thread.sleep(5_000);
        myThread.interrupt();
        myThread.join();

    }
}

class MyThread extends Thread{
    private static Lock lock = new Lock();
    private static boolean flag = true;

    public MyThread(String threadName){
        super(threadName);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start");
        while(flag){
            synchronized (lock){
                try {
                    System.out.println(Thread.currentThread().getName() + " 正在上网");
                    System.out.println(Thread.currentThread().getName() + " 网络中断，正在等在网络恢复");
                    lock.wait(5_000);
                    System.out.println(Thread.currentThread().getName() + " 网络恢复正常, " +
                            "继续上网");

//                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("***************************************");
                    System.out.println(Thread.currentThread().getName() + " gets interrupted");
                    System.out.println(Thread.currentThread().getName() + " 去吃饭了");
                    try {
                        Thread.sleep(5_000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    System.out.println("***************************************");

//                    return;
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " run method exit");
    }
}

class Lock{}