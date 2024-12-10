package sdms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sdms.util.DateManager;

@SpringBootApplication
public class SmilingDentistManagerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmilingDentistManagerServerApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	DateManager dateManager() {
		return new DateManager();
	}
	
}
