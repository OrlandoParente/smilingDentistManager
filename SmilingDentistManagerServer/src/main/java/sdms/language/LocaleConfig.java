package sdms.language;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

	private final Logger LOGGER = LoggerFactory.getLogger( LocaleConfig.class );
	
//    @Autowired
//    private LocaleResolver customLocaleResolver;

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        LOGGER.info("Intercepting request for locale change");
        return lci;
    }

//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        return new LocaleChangeInterceptor() {
//        	@Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
//                LOGGER.info("Intercepting request for locale change");
//                return super.preHandle(request, response, handler);
//            }
//        };
//    }

    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        LOGGER.info("Registering CustomLocaleResolver");
        return new CustomLocaleResolver(); // Crea un'istanza del CustomLocaleResolver
    }

    
}
