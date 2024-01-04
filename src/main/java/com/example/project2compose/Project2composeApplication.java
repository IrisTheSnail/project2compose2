package com.example.project2compose;

import com.example.project2compose.core.generators.dockerfile.DockerfileGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project2composeApplication {

	public static void main(String[] args) {
		DockerfileGenerator g = DockerfileGenerator.getInstance();

		SpringApplication.run(Project2composeApplication.class, args);
	}

}
