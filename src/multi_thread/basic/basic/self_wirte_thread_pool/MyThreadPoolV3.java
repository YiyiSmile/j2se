package multi_thread.basic.basic.self_wirte_thread_pool;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Tom
 * @Date 2020/5/28 11:17
 * @Version 1.0
 * @Description
 * add more contoll variable, min,active,max. Initial value is min, when is not enough,
 * increase the threads number to active till max. If the workloads decline, then reduce
 * the thread number.
 */
public class MyThreadPoolV3 extends Thread{
    private static final int DEFAULT_THREAD_POOL_SIZE = 10;
    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;
    private static final DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{
        throw new DiscardException("Discard this task");
    };
    private static final String THREAD_NAME_PREFIX = "SIMPLE_THREAD_POOL";
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("POOL_GROUP");
    private static volatile int seq = 0;
    private int threadPoolSize;//current number of threads, vary dynamically
    private int min;
    private int active;
    private int max;

    private final int taskQueueSize;
    private final DiscardPolicy discardPolicy;
    private final LinkedList<Runnable> taskQueue;//task queue
    private final List<Worker> workerList;//thread queue
    private volatile boolean isDestroyed = false;
    private enum WorkerState{
        FREE,RUNNING,BLOCKED,DEAD
    }


    public MyThreadPoolV3(){
        this(4,8,16,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
    }
//    public MyThreadPoolV3(int poolSize){
//        this(poolSize,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY);
//    }
//    public MyThreadPoolV3(int poolSize, int taskQueueSize){
//        this(poolSize,taskQueueSize,DEFAULT_DISCARD_POLICY);
//    }
    public MyThreadPoolV3(int min, int active, int max,int taskQueueSize, DiscardPolicy discardPolicy){
        this.min = min;
        this.active = active;
        this.max = max;
        this.taskQueueSize = taskQueueSize;
        this.discardPolicy = discardPolicy;
        this.taskQueue = new LinkedList<>();
        this.workerList = new ArrayList<>();
        init();
    }
    private void init(){
        for(int i = 0; i<min;i++){
            createWorker();
        }
        this.threadPoolSize = min;
        this.start();//Start the monitor thread during construction phase instead of call
        //thread.start() outside
    }
    private void createWorker(){
        String threadName = THREAD_NAME_PREFIX + (seq++);
        Worker worker = new Worker(THREAD_GROUP,threadName);
        System.out.println(threadName + " gets created!");
        worker.start();
        workerList.add(worker);
    }
    public void submit(Runnable task){
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

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long endTime;
        while(!isDestroyed){
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    min,active,max, threadPoolSize,taskQueue.size());

            try {
                Thread.sleep(3_000);
                if(active<taskQueue.size()&&threadPoolSize<active){
                    for(int i=threadPoolSize;i<active;i++) {
                        createWorker();
                        threadPoolSize++;
                    }
                    System.out.println("Pool size was increased from "+ min + " to " + active);
                }else if(max<taskQueue.size() && threadPoolSize<max){
                    for(int i=threadPoolSize;i<max;i++) {
                        createWorker();
                        threadPoolSize++;
                    }
                    System.out.println("Pool size was increased from "+ active + " to " + max);
                }
                if(taskQueue.size() == 0 && threadPoolSize>active){
                    Thread.sleep(10_000);
                    System.out.println("Start to reduce the thread pool size!");
                    int releaseSize = threadPoolSize - active;
                    synchronized (taskQueue){

                        for (Iterator<Worker> iterator = workerList.iterator();iterator.hasNext();){
                            if(releaseSize <= 0) break;
                            Worker worker = iterator.next();
                            worker.close();
                            worker.interrupt();
                            iterator.remove();
                            releaseSize--;
                        }
                        threadPoolSize = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public int getMin() {
        return min;
    }

    public int getActive() {
        return active;
    }

    public int getMax() {
        return max;
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
                    //浣跨敤while寰幆浠ｆ浛if else鐨勫ソ澶勬槸锛岃notify涔嬪悗锛屼笉闇�瑕佹瘡娆″垽鏂璽ask鏄惁鏄┖锛岀劧鍚�
                    //灏濊瘯鎵ц锛屽氨姣斿涓嬭竟鐨勬敞閲婃帀鐨勪唬鐮併�傝繖鏍锋晥鐜囨洿楂樸��
                    while (taskQueue.size() == 0) {
                        state = WorkerState.BLOCKED;
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
//                            e.printStackTrace();
                            System.out.println(Thread.currentThread().getName() + " get destroyed!");
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

    private static MyThreadPoolV3 myThreadPool = new MyThreadPoolV3();
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Submit 40 tasks for the first time");
        submitTask();
        Thread.sleep(20_000);
//        myThreadPool.shutdown();
//        submitTask();
   }

    private static void submitTask(){
        for (int i = 0; i < 40; i++) {
            myThreadPool.submit(new MyTask(i));
            System.out.println("Task " + i + " is submitted");

        }
    }

    static class MyTask implements Runnable{
        private int taskId;
        public MyTask(int taskId){ this.taskId = taskId;}

        @Override
        public void run() {

            System.out.println("Task " + taskId + " is being served by " +
                    Thread.currentThread().getName());
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//                System.out.println("Current Time ->" + new Timestamp(System.currentTimeMillis()));
        }


    }

}
