package multi_thread.juc.utils.shared_lock;

import java.util.concurrent.Exchanger;

/**
 * @Author Tom
 * @Date 2020/6/2 15:36
 * @Version 1.0
 * @Description
 * 1. validate Exchanger exchange the instance itself or a copy of the instance.
 * Conclusion: instance itself was exchanged.
 *2. Thread A can access the object instance defined in Thread B, this could cause the
 * thread-safety issue. So when using Exchanger, needs to be very carefully regards
 * thread-safety.
 * output:
 * "C:\Program Files\Java\jdk1.8.0_251\bin\java.exe" "-javaagent:D:\Software\IntelliJ IDEA 2020.1.1\lib\idea_rt.jar=61106:D:\Software\IntelliJ IDEA 2020.1.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_251\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_251\jre\lib\rt.jar;D:\Java\IdeaProjects\j2se\out\production\j2se;D:\Software\IntelliJ IDEA 2020.1.1\lib\junit.jar;D:\Software\IntelliJ IDEA 2020.1.1\lib\junit-4.12.jar;D:\Software\IntelliJ IDEA 2020.1.1\lib\hamcrest-core-1.3.jar" multi_thread.juc.utils.ExchangerExample1
 * Thread B send object java.lang.Object@3812167c
 * Thread A send object java.lang.Object@67be9d05
 * Thread B received object java.lang.Object@67be9d05
 * Thread A received object java.lang.Object@3812167c
 */
public class ExchangerExample1 {
    public static void main(String[] args) {
        Exchanger<Object> ex = new Exchanger<>();
        new Thread(){
            @Override
            public void run() {
                Object objA = new Object();
                try {
                    Object objReceived = ex.exchange(objA);
                    System.out.println("Thread B send object " + objA);
                    System.out.println("Thread B received object " + objReceived);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Object objB = new Object();
                try {
                    Object objReceived = ex.exchange(objB);
                    System.out.println("Thread A send object " + objB);
                    System.out.println("Thread A received object " + objReceived);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
