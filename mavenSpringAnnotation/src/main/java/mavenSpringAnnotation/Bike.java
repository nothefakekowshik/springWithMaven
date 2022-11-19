package mavenSpringAnnotation;

import org.springframework.stereotype.Component;

@Component

public class Bike implements Vehicle {
	public void move()
	{
		System.out.println("bike is moving");
	}
}
