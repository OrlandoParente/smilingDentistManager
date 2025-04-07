package sdms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.AntPathMatcher;
import sdms.util.DateAndTimeManager;

@SpringBootApplication
@EnableScheduling
public class SmilingDentistManagerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmilingDentistManagerServerApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	DateAndTimeManager dateAndTimeManager() {
		return new DateAndTimeManager();
	}
	
	@Bean
	AntPathMatcher antPathMatcher() {
		return new AntPathMatcher();
	}

}
