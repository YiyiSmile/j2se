package multi_thread.basic.basic.self_wirte_thread_pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Tom
 * @Date 2020/5/28 11:17
 * @Version 1.0
 * @Description
 * Add the reject policy and shutdown function for the self defined thread pool
 */
public class MyThreadPoolV2 {
    private static final int DEFAULT_THREAD_POOL_SIZE = 10;
    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;
    private static final DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{
        throw new DiscardException("Discard this task");
    };
    private static final String THREAD_NAME_PREFIX = "SIMPLE_THREAD_POOL";
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("POOL_GROUP");
    private static volatile int seq = 0;
    private final int threadPoolSize;
    private final int taskQueueSize;
    private final DiscardPolicy discardPolicy;
    private final LinkedList<Runnable> taskQueue;//task queue
    private final List<Worker> workerList;//thread queue
    private volatile boolean isDestroyed = false;
    private enum WorkerState{
        FREE,RUNNING,BLOCKED,DEAD
    }


    public MyThreadPoolV2(){
        this(DEFAULT_THREAD_POOL_SIZE,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }
    public MyThreadPoolV2(int poolSize){
        this(poolSize,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }
    public MyThreadPoolV2(int poolSize, int taskQueueSize){
        this(poolSize,taskQueueSize,DEFAULT_DISCARD_POLICY);
    }
    public MyThreadPoolV2(int poolSize, int taskQueueSize, DiscardPolicy discardPolicy){
        this.threadPoolSize = poolSize;
        this.taskQueueSize = taskQueueSize;
        this.discardPolicy = discardPolicy;
        this.taskQueue = new LinkedList<>();
        this.workerList = new ArrayList<>();
        init();
    }
    private void init(){
        for(int i = 0; i<threadPoolSize;i++){
            Worker worker = new Worker(THREAD_GROUP,THREAD_NAME_PREFIX + (seq++));
            worker.start();
            workerList.add(worker);
        }
    }
    private void submit(Runnable task){
        if(isDestroyed) throw new IllegalStateException("The thread pool has already been " +
                "destroyed, it's not allowed to submit task to it.");
        synchronized (taskQueue){
//            if(taskQueue.size() >= taskQueueSize) discardPolicy.discard();
            taskQueue.addFirst(task);
            taskQueue.notifyAll();
        }
    };

    public void shutdown() throws InterruptedException {
        while(!taskQueue.isEmpty()){
            Thread.sleep(50);
        }
        int initVal = workerList.size();
        while(initVal > 0){
            for (Worker worker : workerList) {
                if(worker.state == WorkerState.BLOCKED) {
                    worker.interrupt();
                    worker.close();
                    initVal--;
                }else{
                    Thread.sleep(10);
                }
            }
        }
        this.isDestroyed = true;
        System.out.println("Thread pool was shutdown!");
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public int getTaskQueueSize() {
        return taskQueueSize;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public static class DiscardException extends RuntimeException{
        public DiscardException(String msg){
            super(msg);
        }
    }
    public interface DiscardPolicy{
        void discard() throws DiscardException;
    }

    private class Worker extends Thread{
        public Worker(ThreadGroup threadGroup,String threadName){
            super(threadGroup,threadName);
        }
        private WorkerState state = WorkerState.FREE;


        public WorkerState getWorkerState() {
            return state;
        }

        public void close(){
            this.state = WorkerState.DEAD;
        }

        @Override
        public void run() {
            Runnable task = null;
            OUTER:
            while(state != WorkerState.DEAD) {
                synchronized (taskQueue) {
                    //使用while循环代替if else的好处是，被notify之后，不需要每次判断task是否是空，然后
                    //尝试执行，就比如下边的注释掉的代码。这样效率更高。
                    while (taskQueue.size() == 0) {
                        state = WorkerState.BLOCKED;
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
//                            e.printStackTrace();
                            break OUTER;
                        }
                    }
                    task = taskQueue.removeLast();
                }
                state = WorkerState.RUNNING;
                task.run();
                state = WorkerState.FREE;
/*                    if(taskQueue.size() != 0) {
                        task = taskQueue.removeLast();
                    }else{
                        try {
                            state = WorkerState.BLOCKED;
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(task!=null) {
                    state = WorkerState.RUNNING;
                    task.run();
                }*/
            }
        }
    }

    private static MyThreadPoolV2 myThreadPool = new MyThreadPoolV2(6,10);
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Submit 40 tasks for the first time");
        submitTask();
        Thread.sleep(20_000);
        myThreadPool.shutdown();
        submitTask();
   }

    private static void submitTask(){
        for (int i = 0; i < 40; i++) {
            myThreadPool.submit(new Runnable() {
                @Override
                public void run() {

                    System.out.println("This runnable task is being served by " +
                            Thread.currentThread().getName());
                    try {
                        Thread.sleep(3_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                System.out.println("Current Time ->" + new Timestamp(System.currentTimeMillis()));
                }
            });

        }
    }

}
