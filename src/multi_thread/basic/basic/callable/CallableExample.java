package multi_thread.basic.basic.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tom
 * @Date 2020/6/3 21:07
 * @Version 1.0
 * @Description
 */
public class CallableExample implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " is started, computing the result, please wait ...");
        TimeUnit.SECONDS.sleep(3);
        return 10;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableExample());
        System.out.println(Thread.currentThread().getName() + " started a new thread.");
        new Thread(futureTask).start();
        System.out.println(Thread.currentThread().getName() + " is doing something else now");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " finished its work, starting to get the result " +
                "from futuretask");
        System.out.println(Thread.currentThread().getName() + " get the result in futuretask from another thread ->" +
                futureTask.get());
    }
}
