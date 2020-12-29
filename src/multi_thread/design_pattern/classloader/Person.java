package multi_thread.design_pattern.classloader;

/**
 * @Author Tom
 * @Date 2020/5/27 21:42
 * @Version 1.0
 * @Description
 */
public class Person {
    String name;
    int age;

    static Address addr;
    static{
        addr = new Address("dalian", "liaoning");
    }



    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
