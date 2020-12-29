package multi_thread.basic.basic.exeption_hanle;

/**
 * @Author Tom
 * @Date 2020/5/28 10:20
 * @Version 1.0
 * @Description
 */
public class Test2 {
    public static void main(String[] args) {

        System.out.println("main start");

        Thread myThread = new Thread("myThread") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                Utility.dosome();
                System.out.println(Thread.currentThread().getName() + " end");
            }
        };
        myThread.start();
        try {
            myThread.join();
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Utility.dosome();

        System.out.println("main end");
    }

}

class Utility {
    private static Some some = new Some();

    public static void dosome() {
        System.out.println("dosome start");
        some.hi();
        System.out.println("dosome end");
    }
}

class Some {
    private String name;

    public void hi() {
        System.out.println("hi start");
        name.replace('a', 'b');
        System.out.println("hi end");
    }
}
