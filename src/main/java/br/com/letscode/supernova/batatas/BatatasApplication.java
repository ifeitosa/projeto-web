package br.com.letscode.supernova.batatas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class BatatasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatatasApplication.class, args);
	}

}
