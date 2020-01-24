package aspertime;



import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import aspertime.Controller.homeController;

@SpringBootApplication
@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class})
public class ASperTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ASperTimeApplication.class, args);
	}

}
