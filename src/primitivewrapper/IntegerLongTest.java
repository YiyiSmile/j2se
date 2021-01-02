package primitivewrapper;

import org.junit.Test;

/**
 * @Author: Tom
 * @Date: 2021年1月2日 下午2:07:27   
 * @Version: 1.0
 * @Description:
 */
public class IntegerLongTest {
	
	@Test
	public void test01() {
		Integer a = new Integer(10);
		Integer b = new Integer("10");
		int c = 10;
		System.out.println(a == b);
		System.out.println(a.intValue() == b.intValue());
		System.out.println(a.intValue() == c);
		System.out.println(a == c);
		//result:
//		false
//		true
//		true
//		true

		
		}

}
