
package generic;

import java.lang.reflect.Field;

import org.junit.Test;

import com.sun.javafx.collections.IntegerArraySyncer;

/**
 * @Author: Tom098
 * @Date: 2020年12月29日
 * @Version: 1.0
 * @Description: Validate the type used for generic is type of Object or the basic type.
 *               For example, for class A<T>, the actual type of T is Object. 
 *               for class A<T extends B>, here B is not a generic type, like the one we used 
 *               in here is Score class. then the actrual type will be type B when the class is
 *               loaded to memory. 
 */
public class Test01 {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> class1 = Class.forName("generic.Student");
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName() + "->" + field.getType().getName());
		}

	}
	@Test
	public void test() {
		Student<IntScore> student = new Student<IntScore>(1,"tom",new IntScore(90));
		Field[] declaredFields = student.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			System.out.println(field.getName() + "->" + field.getType().getName());
			System.out.println("**");
		}
	}

}

class Student<T extends Score> {

	Integer id;
	String name;
	T score;

	public Student(Integer id, String name, T score) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getScore() {
		return score;
	}

	public void setScore(T score) {
		this.score = score;
	}
}

class Score{}

class IntScore extends Score{
	private int score;
	
	public IntScore(int score) {
		super();
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
