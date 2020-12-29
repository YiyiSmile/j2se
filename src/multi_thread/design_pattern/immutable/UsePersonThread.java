package multi_thread.design_pattern.immutable;

/**
 * @Author Tom
 * @Date 2020/5/27 14:15
 * @Version 1.0
 * @Description
 */
public class UsePersonThread extends Thread {
    private Person person;

    public UsePersonThread(String name, Person person) {
        super(name);
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +" print " + person.toString());
    }
}
