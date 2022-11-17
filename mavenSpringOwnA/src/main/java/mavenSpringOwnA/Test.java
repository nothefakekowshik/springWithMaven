package mavenSpringOwnA;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("xmlData.xml");
		Vehicle vObj = (Vehicle)context.getBean("carObj");
		Vehicle vObj2 = (Vehicle)context.getBean("bikeObj");
		vObj.move();
		vObj2.move();
	}

}
