package io;

import java.io.*;

/**
 * @Author Tom
 * @Date 2020/12/11 17:36
 * @Version 1.0
 * @Description: Constructor "Person()" is invoked
 */
public class PersonExternal implements Externalizable {

    public static void main(String[] args) {
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

    String name;
    int age;

    public PersonExternal() {
        System.out.println("Constructor \"Person()\" is invoked");
    }

    public PersonExternal(String name, int age) {

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (String)in.readObject();
        this.age = in.readInt();
    }
}
