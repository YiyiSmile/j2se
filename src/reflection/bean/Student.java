package reflection.bean;

import reflection.annotation.MyField;
import reflection.annotation.MyTable;

/**
 * @Author: Tom
 * @Date: 2020年12月30日 下午1:56:24   
 * @Version: 1.0
 * @Description:
 */

@MyTable("tb_student")
public class Student {
	
	@MyField(columName = "id",type="varchar",length = 10)
	private int id;
	@MyField(columName = "sname",type="varchar",length = 10)
	private String studentName;
	@MyField(columName = "age",type="int",length = 3)
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
