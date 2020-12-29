package multi_thread.design_pattern.classloader;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author Tom
 * @Date 2020/5/27 21:31
 * @Version 1.0
 * @Description
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        System.out.println(loader);


        ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
        System.out.println(loader2);

        Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("D:\\Java\\IdeaProjects\\j2se\\src\\multi_thread\\design_pattern\\classloader"));
        System.out.println(Thread.currentThread().getContextClassLoader());

        Class<Person> c = (Class<Person>) Thread.currentThread().getContextClassLoader().loadClass("multi_thread.design_pattern.classloader.Person");
        Constructor<Person> constructor = c.getConstructor( String.class, int.class);
        Person tom = constructor.newInstance("tom",10);
        System.out.println(tom);
        System.out.println(tom.getClass().getClassLoader());
//        System.out.println(c);
//        System.out.println(c.getClassLoader());
//        System.out.println(Thread.currentThread().getContextClassLoader().getParent());
//        System.out.println(c.getField("addr").getClass());
//        System.out.println(Person.addr.getClass().getClassLoader());

    }
}
