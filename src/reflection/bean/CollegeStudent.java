package reflection.bean;

import reflection.annotation.MyField;

/**
 * @Author: Tom
 * @Date: 2020年12月30日 下午2:15:49   
 * @Version: 1.0
 * @Description:
 */
public class CollegeStudent extends Student{
	@MyField(columName = "grade",type="varchar",length = 10)
	private String grade;
	private String address;

}
