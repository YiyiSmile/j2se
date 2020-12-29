package userfull_class;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * @Author Tom
 * @Date 2020/12/14 14:06
 * @Version 1.0
 * @Description
 */
public class StringTest01 {

    //find out the same substring with  maximum length among two given strings
    @Test
    public void test01() {
        //common string is "hijklmnopqr"
        String str1 = "abcdefghijklmnopqrstuvw";
        String str2 = "abcfafghijklmnopqratuvw";

        String maxSameString = getMaxSameString(str1, str2);
        System.out.println("Max Same subString: " + maxSameString);


    }

    //Test the string memory representation.
    @Test
    public void test02() {
        String str1 = "hello";
        String str2 = "world";
        String str3 = "hello" + "world";
        String str4 = str1 + "world";//point to string in heap
        String str5 = "hello" + str2;//point to string in heap
        String str6 = str1 + str2; //point to string in heap
        String str7 = (str1 + str2).intern(); //point to string in constant pool (method area)

        System.out.println(str3 == str4);
        System.out.println(str5 == str4);
        System.out.println(str6 == str3);
        System.out.println(str7 == str3);
        System.out.println(str3.equals(str4));
        System.out.println(str3.equals(str5));
    }
    //Test string charset encoding
    @Test
    public void test03() throws UnsupportedEncodingException {
        String str1 = "中";
        System.out.println(str1.getBytes().length);
        System.out.println(str1.getBytes("GBK").length);
        System.out.println(str1.getBytes("UTF-8").length);
        System.out.println(str1.getBytes("ISO8859-1").length);

        String str2 = new String(str1.getBytes(), "GBK");
        //乱码
        System.out.println(str2);
        String str3 = new String(str1.getBytes("GBK"), "GBK");
        System.out.println(str3);
    }

    //3 ways to reverse a string
    @Test
    public void test04() {
        String str1 = "abcdefghi";
        String reverseStr = reverse(str1, 0, str1.length() - 1);
        String reverseStr1 = reverse1(str1, 0, str1.length() - 1);
        String reverseStr2 = reverse2(str1, 0, str1.length() - 1);
        System.out.println(reverseStr);
        System.out.println(reverseStr1);
        System.out.println(reverseStr2);

    }

    //Sort the characters in a string in natural order
    @Test
    public void test05() {
        String str = "laksdjfasdkfasdfasdfwerut";
        char[] chars = str.toCharArray();
        //if we don't use the existing sort() function, we can convert the char array to
        //int array, then we can write a sort method like bubble, select, merge etc. to sort
        //the int array, then convert the int array to char array and construct a new string
        //with the new array.
/*        Arrays.sort(chars);
        String str1 = new String(chars);
        System.out.println(str1);*/

        //convert char array to byte array
        byte[] byteArray = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            byteArray[i] = (byte)chars[i];
        }
        //sort against byte array
        bubbleSort(byteArray);

        //convert byte array to char array
/*        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)intArray[i];
        }*/

        System.out.println("String after sort: " + new String(byteArray));




    }

    //Count the number of occurrence of a string in antoher string
    @Test
    public void test06() {

    }

    @Test
    public void test07() {

    }

    //3 methods to reverse a string
    //1. convert to a char array
    public String reverse(String str, int startIndex, int endIndex) {
        if (str == null || "".equals(str)) throw new IllegalArgumentException("argument str can't be null or \"\"");
        char[] chars = str.toCharArray();
        char temp;
        for (int i = startIndex, j = endIndex; i < j; i++, j--) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    //2. concatenate string
    public String reverse1(String str, int startIndex, int endIndex) {
        String reverseString = str.substring(0, startIndex);
        for (int i = endIndex; i >= startIndex; i--) {
            reverseString += str.charAt(i);
        }
        reverseString += str.substring(endIndex + 1);
        return reverseString;
    }

    //3. use StringBuilder to concatenate the string
    public String reverse2(String str, int startIndex, int endIndex) {
        StringBuilder stringBuilder = new StringBuilder(str.substring(0, startIndex));
        for (int i = endIndex; i >= startIndex; i--) {
            stringBuilder.append(str.charAt(i));
        }
        stringBuilder.append(str.substring(endIndex + 1));
        return stringBuilder.toString();
    }

    public int getOccurrence(String mainString, String subString){
        if(mainString.length() < subString.length()) throw new IllegalArgumentException("The length of the SubString must be shorted or equal to the length of main string.");
        int count = 0;
        int index = 0;
        while((index = mainString.indexOf(subString))!=-1){
            count++;
            mainString = mainString.substring(index + mainString.length());
        }
        return count;
    }

    //This method will not return all the same string with same length, but only return the first one.
    public String getMaxSameString(String str1, String str2){
        if(str1 == null || "".equals(str1)) throw new IllegalArgumentException("The given string can't be null or blank!");
        if(str2 == null || "".equals(str2)) throw new IllegalArgumentException("The given string can't be null or blank!");

        String stringMax = str1.length() >= str2.length()? str1:str2;
        String stringMin = str1.length() < str2.length()? str1:str2;

        int length = stringMin.length();

        for (int i = 0; i < length; i++) {
            for(int x = 0, y = length - i;y < length; x++,y++){
                String substring = stringMin.substring(x, y);
                if(stringMax.contains(substring)) return substring;
            }
        }
        return null;
    }

    public void bubbleSort(byte[] array){
        if(array == null || array.length == 0) throw new IllegalArgumentException("The given array can't be blank.");
        byte temp = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j <array.length -i; j++) {
                if(array[j] > array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}

