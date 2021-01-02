package reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Tom
 * @Date: 2020年12月30日 下午1:58:23   
 * @Version: 1.0
 * @Description:
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyField {
	String columName();
	String type();
	int length();
}
