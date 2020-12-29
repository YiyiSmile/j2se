package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Tom
 * @Date 2020/12/5 15:18
 * @Version 1.0
 * @Description
 *
 * 测试通过反射方式调用抛出异常的目标对象方法时，异常是否会被调用方法捕捉到
 * 结果：默认不会捕捉到，但是会捕捉到InvocationTargetException，通过如下方式：
 * InvocationTargetException.getCause();
 * 可以获取到原始的Exception对象
 */
public class InvocationTargetExceptionTest {
    public void test(){
        System.out.println("test is carried out.");
        throw new NullPointerException("Hello");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = InvocationTargetExceptionTest.class.getDeclaredMethod("test");
        InvocationTargetExceptionTest test = new InvocationTargetExceptionTest();
        try {
            method.invoke(test);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            System.out.println("Original exception which is thrown by the target method is -> " + cause);
        }
/*        InvocationTargetExceptionTest test = new InvocationTargetExceptionTest();
        test.test();*/

    }
}
