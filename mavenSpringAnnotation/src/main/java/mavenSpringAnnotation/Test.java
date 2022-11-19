package mavenSpringAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("xmlData.xml");
		
		Vehicle vObjCar = (Vehicle) context.getBean("car");
		Vehicle vObjBike = (Vehicle) context.getBean("bike");
		
		vObjCar.move();
		vObjBike.move();
	}

}
