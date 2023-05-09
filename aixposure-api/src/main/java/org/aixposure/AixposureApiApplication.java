package org.aixposure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController

public class AixposureApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AixposureApiApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "HELLO MY BOY !";
	}
}
