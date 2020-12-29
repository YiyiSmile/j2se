package classloader;

import java.io.*;

/**
 * @Author Tom
 * @Date 2020/5/30 21:00
 * @Version 1.0
 * @Description
 */
public class Test {
    public static void main(String[] args) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("a.txt")));

    }

}
