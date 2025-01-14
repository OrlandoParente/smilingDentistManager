//package sdms.language;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.web.servlet.LocaleResolver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Configuration
//public class CustomLocaleResolverConfig {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(CustomLocaleResolverConfig.class);
//
//    @Bean
//    @Primary
//    public LocaleResolver customLocaleResolver() {
//        LOGGER.info("Registering CustomLocaleResolver");
//        return new CustomLocaleResolver();
//    }
//}
