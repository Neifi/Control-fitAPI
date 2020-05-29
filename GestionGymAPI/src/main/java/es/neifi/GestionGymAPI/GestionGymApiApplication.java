package es.neifi.GestionGymAPI;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

import es.neifi.GestionGymAPI.rest.services.StorageService;

@SpringBootApplication
@EnableJpaAuditing
public class GestionGymApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionGymApiApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner init(StorageService storageService) {
		
		return args -> {
			
			storageService.deleteAll();
			storageService.init();
		};
	}
	
	
}
