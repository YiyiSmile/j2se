package io;

import org.junit.Test;

import java.io.*;

/**
 * @Author Tom
 * @Date 2020/12/11 17:37
 * @Version 1.0
 * @Description
 */
public class ExternalSerializationTest {
    public static void main(String[] args) {
        final PersonExternal tom = new PersonExternal("tom", 20);
    }
    @Test
    public void test01(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Java\\IdeaProjects\\j2se\\resources\\person-external.txt"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\Java\\IdeaProjects\\j2se\\resources\\person-external.txt"))) {
            PersonExternal tom = new PersonExternal("tom", 20);
            oos.writeObject(tom);
            //Constructor "Person()" is invoked
//            PersonExternal personExternal = (PersonExternal) ois.readObject();
            Object o = ois.readObject();
            System.out.println(o);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
