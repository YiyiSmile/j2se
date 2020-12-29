package io;

import org.junit.Test;

import java.io.*;

/**
 * @Author Tom
 * @Date 2020/12/11 17:10
 * @Version 1.0
 * @Description
 *
 * No constructor is called when using ois.readObject() to create object.
 */
public class SerializationTest01 {

    @Test
    public void test01(){
        Person tom = new Person("tom", 20);
        final Person henry = new Person("henry", 20);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                "D:\\Java\\IdeaProjects\\j2se\\resources\\person.txt"));
            final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                    "D:\\Java\\IdeaProjects\\j2se\\resources\\person.txt"));){
            oos.writeObject(tom);

            //No constructor is called when using ois.readObject() to create object.
            Person tom2 = (Person)ois.readObject();
            System.out.println(tom2);
            System.out.println(tom == tom2);//false

            tom2.setAge(30);
            System.out.println(tom2);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


