package multi_thread.basic.basic.self_wirte_thread_pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Tom
 * @Date 2020/5/28 11:17
 * @Version 1.0
 * @Description
 * initial version, with very limited function.
 *
 */
public class MyThreadPoolV1 {
    private static final int DEFAULT_SIZE = 10;
    private static final String THREAD_NAME_PREFIX = "SIMPLE_THREAD_POOL";
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("POOL_GROUP");
    private static volatile int seq = 0;
    private int size;
    private  LinkedList<Runnable> taskQueue;//task queue
    private  List<Worker> workerList;//保存线程
    private enum WorkerState{
        FREE,RUNNING,BLOCKED,DEAD
    }


    public MyThreadPoolV1(){
        this(DEFAULT_SIZE);
    }
    public MyThreadPoolV1(int poolSize){
        this.size = poolSize;
        this.taskQueue = new LinkedList<>();
        this.workerList = new ArrayList<>();
        init();
    }
    private void init(){
        for(int i = 0; i<size;i++){
            Worker worker = new Worker(THREAD_GROUP,THREAD_NAME_PREFIX + (seq++));
            worker.start();
            workerList.add(worker);
        }
    }
    private void submit(Runnable task){
        synchronized (taskQueue){
            taskQueue.addFirst(task);
            taskQueue.notifyAll();
        }
    };



    private class Worker extends Thread{
        public Worker(ThreadGroup threadGroup,String threadName){
            super(threadGroup,threadName);
        }
        private WorkerState state = WorkerState.FREE;


        public WorkerState getWorkerState() {
            return state;
        }

        @Override
        public void run() {
            Runnable task = null;
            while(state != WorkerState.DEAD) {
                synchronized (taskQueue) {
                    //使用while循环代替if else的好处是，被notify之后，不需要每次判断task是否是空，然后
                    //尝试执行，就比如下边的注释掉的代码。这样效率更高。
                    while (taskQueue.size() == 0) {
                        state = WorkerState.BLOCKED;
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
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

    private static MyThreadPoolV1 myThreadPool = new MyThreadPoolV1();
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Submit 40 tasks for the first time");
        submitTask();
        Thread.sleep(30_000);
        System.out.println("******************************************");
        System.out.println("******************************************");
        System.out.println("Submit 40 tasks for the second time");
        submitTask();

    }

    private static void submitTask(){
        for (int i = 0; i < 40; i++) {
            myThreadPool.submit(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("Current Time ->" + new Timestamp(System.currentTimeMillis()));
                    System.out.println(Thread.currentThread().getName() + "-> is working on " +
                            "task ");
                    try {
                        Thread.sleep(3_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(Thread.currentThread().getName() + "-> finished " +
//                            "task ");
//                    System.out.println("Current Time ->" + new Timestamp(System.currentTimeMillis()));
                }
            });
        }
    }

}
