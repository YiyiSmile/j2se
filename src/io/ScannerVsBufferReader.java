package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @Author Tom
 * @Date 2020/12/6 15:40
 * @Version 1.0
 * @Description
 *
 * //Scanner拥有多种方法，可以直接接受键盘输入，直接读取int, long,
 * double,string等多种不同类型，比如nextInt()返回整形。nextLine返回一个字符串
 *
 * 而bufferedreader主要面向字符流，主要用来读取字符。
 * Sr. No.
 * Key
 * Scanner Class
 * BufferReader Class
 * 1
 * Synchronous
 * Scanner is not syncronous in nature and should be used only in single threaded case.
 * BufferReader is syncronous in nature. During multithreading environment, BufferReader should be used.
 * 2
 * Buffer Memory
 * Scanner has little buffer of 1 KB char buffer.
 * BufferReader has large buffer of 8KB byte Buffer as compared to Scanner.
 * 3
 * Processing Speed
 * Scanner is bit slower as it need to parse data as well.
 * BufferReader is faster than Scanner as it only reads a character stream.
 * 4
 * Methods
 * Scanner has methods like nextInt(), nextShort() etc.
 * BufferReader has methods like parseInt(), parseShort() etc.
 * 5
 * Read Line
 * Scanner has method nextLine() to read a line.
 * BufferReader has method readLine() to read a line.
 */
public class ScannerVsBufferReader {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Scanner scanner = new Scanner(System.in)){
            //1. 读取整形数据
            System.out.println("Enter a number:");
            System.out.println("You entered: " + Integer.parseInt(br.readLine()));
            System.out.println("Enter another number:");
            System.out.println("You entered: " + scanner.nextInt());
            //2.读取字符串
            System.out.println("Enter a string:");
            System.out.println("You entered: " + br.readLine());
            System.out.println("Enter another string:");
            System.out.println("You entered: " + scanner.nextLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
