package io;

import java.io.*;

/**
 * @Author Tom
 * @Date 2020/12/11 17:36
 * @Version 1.0
 * @Description
 */
public class Person implements Serializable {

    public static void main(String[] args) {
        Person tom = new Person("tom", 20);

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

    String name;
    int age;

    public Person() {
        System.out.println("Constructor \"Person()\" is invoked");
    }

    public Person(String name, int age) {

        this.name = name;
        this.age = age;
        System.out.println("Constructor \"Person(String name, int age)\" is invoked");
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
