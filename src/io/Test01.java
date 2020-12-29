package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author Tom
 * @Date 2020/12/5 20:54
 * @Version 1.0
 * @Description
 *  * 文件字节输出流
 *  *1、创建源
 *  *2、选择流
 *  *3、操作(写出内容)
 *  *4、释放资源
 */
public class Test01 {
    public static void main(String[] args)  {
        File file = new File("examScore.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            String temp = "IO is so easy!";
            byte[] bytes = temp.getBytes();

            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != fos){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
