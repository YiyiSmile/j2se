package io;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * @Author Tom
 * @Date 2020/12/6 16:00
 * @Version 1.0
 * @Description
 */
public class ScannerTest {
    //Test 1
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Please enter Name, Age, Salary:");
            String name = scanner.nextLine();
            int age = scanner.nextInt();
            double salary = scanner.nextDouble();
            System.out.printf("You name is %s, Your age is %d, Your salary is %f",
                    name, age, salary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Test2
    @Test
    public void test2(){
        try(Scanner scanner = new Scanner(new FileReader("D:\\Java\\IdeaProjects\\j2se\\resources\\examScore.txt"));
        PrintWriter printWriter = new PrintWriter("D:\\Java\\IdeaProjects\\j2se\\resources\\dest.txt")){
            while(scanner.hasNext()){
                printWriter.write(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
