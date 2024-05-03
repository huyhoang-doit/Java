package vn.hdi.spring.constructorinjection.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"vn.hdi.spring.constructorinjection.application",
				"vn.hdi.spring.constructorinjection.rest",
				"vn.hdi.spring.constructorinjection.service"
		}
)
public class ConstructorInjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstructorInjectionApplication.class, args);
	}

}
