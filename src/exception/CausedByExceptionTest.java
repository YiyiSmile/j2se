package exception;

import java.io.IOException;

/**
 * @Author Tom
 * @Date 2020/12/5 17:20
 * @Version 1.0
 * @Description 测试抛出Exception caused by Exception
 */
public class CausedByExceptionTest {
    public void method1() throws IOException {
        new AnotherClass().method2();
    }

    //Test1:

/*    public static void main(String[] args)  {
        try {
            new CausedByExceptionTest().method1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //Result
    //java.io.IOException: exception throw by method2
    //	at exception.AnotherClass.method2(CausedByExceptionTest.java:27)
    //	at exception.CausedByExceptionTest.method1(CausedByExceptionTest.java:13)
    //	at exception.CausedByExceptionTest.main(CausedByExceptionTest.java:18)

    //Test2
    public void method4() throws MyException {
        try {
            new ThirdClass().method5();
        } catch (IOException e) {
            throw new MyException(e);  //关键语句

        }
    }

    public static void main(String[] args)  {
        try {
            new CausedByExceptionTest().method4();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
    //Result:

    //exception.MyException: java.io.IOException: Exception throw by method3
    //	at exception.CausedByExceptionTest.method4(CausedByExceptionTest.java:36)
    //	at exception.CausedByExceptionTest.main(CausedByExceptionTest.java:42)
    //Caused by: java.io.IOException: Exception throw by method3
    //	at exception.ThirdClass.method3(CausedByExceptionTest.java:58)
    //	at exception.ThirdClass.method5(CausedByExceptionTest.java:61)
    //	at exception.CausedByExceptionTest.method4(CausedByExceptionTest.java:34)

}

class AnotherClass {
    public void method2() throws IOException {
        throw new IOException("exception throw by method2");
    }
}

class ThirdClass {
    public void method3() throws IOException {
        throw new IOException("Exception throw by method3");
    }
    public void method5() throws IOException {
        method3();
    }
}


class MyException extends Exception{
    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }
}


