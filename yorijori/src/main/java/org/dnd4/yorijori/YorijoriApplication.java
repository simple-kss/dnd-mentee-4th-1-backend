package org.dnd4.yorijori;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class YorijoriApplication {

	public static void main(String[] args) {
		SpringApplication.run(YorijoriApplication.class, args);
	}

}
