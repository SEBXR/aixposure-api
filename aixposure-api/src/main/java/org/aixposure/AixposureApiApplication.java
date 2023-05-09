package org.aixposure;

import java.util.List;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController

public class AixposureApiApplication {

	@PersistenceContext
    private EntityManager entityManager;
	public static void main(String[] args) {
		SpringApplication.run(AixposureApiApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "HELLO MY BOY !";
	}
	
	@GetMapping("/articles")
	public List<Object[]> articles(){
		Query query = entityManager.createNativeQuery("SELECT * FROM articles");
		return query.getResultList();
	}
}
