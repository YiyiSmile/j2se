package multi_thread.juc.utils.shared_lock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tom
 * @Date 2020/5/31 7:28
 * @Version 1.0
 * @Description
 * Demonstrate one threads waits for multiple other threads.
 *
 * CountDownLatch:
 *  *  * A synchronization aid that allows one or MORE threads to wait until
 *  *  * a set of operations being performed in other threads completes.
 */
public class CountDownLatchExample1 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        //1. Prepare data
        Integer[] data = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        //2. start multiple threads to process the data.
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < data.length; i++) {
            executorService.submit(new Task(data,i,latch));
        }
        //3. Wait till all threads finished, then move to next step.
        //executorService.shutdown();
        //3.1 solution 1: use executorService.awaitTermination() mehtod
        //executorService.awaitTermination(3_000, TimeUnit.MINUTES);
        //3.2. solution 2: use CountDownLatch
        latch.await(3, TimeUnit.MINUTES);
        System.out.println("Closing the thread pool.");
        executorService.shutdown();
        System.out.println("All data have been processed.");
        System.out.println("The final result is: " + Arrays.toString(data));
        System.out.println("Start to do something else ...");
    }

    static class Task implements Runnable{
        private Integer[] data;
        private int index;
        private CountDownLatch latch;
        public Task(Integer[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;

        }

        @Override
        public void run() {
            /*Get the element of the array according to the given index
            * If the element is even, multiply it by 2
            * If the element is odd, multiply it by 10*/
            try {
                //simulate that it takes some time to process the data.
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int element = data[index];
            if(element%2 == 0){
                data[index] = element*2;
            }else{
                data[index] = element*10;
            }
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " Finish process data -> " +
                    element);
        }
    }
}
