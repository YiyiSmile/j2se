package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Tom
 * @Date 2020/12/6 17:16
 * @Version 1.0
 * @Description
 * 从外部调用类的私有方法
 */
public class CallPrivateMethodTest {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("reflection.A");
            Object o = clazz.newInstance();
            Method declaredMethod = clazz.getDeclaredMethod("print", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(o, "Hello World");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class A{
    private void print(String message){
        System.out.println(message);

    }
}
