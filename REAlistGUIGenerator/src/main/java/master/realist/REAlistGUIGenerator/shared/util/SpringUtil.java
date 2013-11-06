package master.realist.REAlistGUIGenerator.shared.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Utility 
 * 
 * @author Thomas
 *
 */
public class SpringUtil {
	
	public static final ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");;
	
}
