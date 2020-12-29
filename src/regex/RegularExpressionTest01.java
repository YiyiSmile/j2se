package regex;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Tom
 * @Date 2020/12/5 21:36
 * @Version 1.0
 * @Description
 *

 */
public class RegularExpressionTest01 {
    public static void main(String[] args) {
        BufferedReader br = null;
        String temp;
        Pattern pattern = Pattern.compile("[(]\\d{3}[)]\\s\\d{3}-\\d{4}");
        try {
            br = new BufferedReader(new FileReader(new File("D:\\Java\\IdeaProjects\\j2se\\resources\\phone-number.txt")));
            while(null != (temp = br.readLine())){
//                System.out.println(temp);
                Matcher matcher = pattern.matcher(temp);
                if(matcher.find()){
                    System.out.println(matcher.group());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
