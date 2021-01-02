package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.junit.Test;

import com.sun.istack.internal.NotNull;

import reflection.annotation.MyField;

/**
 * @Author: Tom
 * @Date: 2020年12月30日 下午1:40:18   
 * @Version: 1.0
 * @Description:
 */
public class AnnotationTest {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		
		Class clazz = Class.forName("reflection.bean.Student");
		//Get  all annotation for a class
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
		}
		//get specified annotation for a class
		//get field annotation
		Field field = clazz.getDeclaredField("studentName");
		MyField annotation = field.getAnnotation(MyField.class);
		System.out.println(annotation);
	
	}
	
	@Test
	public void test02() throws Exception {
		 Class<?> clazz = Class.forName("reflection.bean.CollegeStudent");
		 Field[] fields = clazz.getDeclaredFields();
		 Class<?> superclass = clazz.getSuperclass();
		 System.out.println(fields.length);
		 for (Field field : fields) {
			System.out.println(field);
		}
		 System.out.println("******");
		 Field[] fields2 = superclass.getDeclaredFields();
		 for (Field field : fields2) {
			System.out.println(field);
		}
	}

}

