package multi_thread.design_pattern.classloader;

/**
 * @Author Tom
 * @Date 2020/5/27 21:42
 * @Version 1.0
 * @Description
 */
public class Man extends Person {
    String wife;

    public Man(String name, int age, String wife) {
        super(name, age);
        this.wife = wife;
    }
}
