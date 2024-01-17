package com.ECESWA.ElectReg;

import com.ECESWA.ElectReg.dao.UsersRepository;
import com.ECESWA.ElectReg.storage.StorageProperties;
import com.ECESWA.ElectReg.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ElectRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectRegApplication.class, args);
	}

/*	@Bean
	CommandLineRunner init(StorageService storageService){
		return args -> {
			storageService.deleteAll();
			storageService.init();
		};
	}*/

}
