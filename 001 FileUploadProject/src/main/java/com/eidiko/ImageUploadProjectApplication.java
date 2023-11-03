package com.eidiko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages ="com.eidiko.service")
public class ImageUploadProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageUploadProjectApplication.class, args);
	}

}
