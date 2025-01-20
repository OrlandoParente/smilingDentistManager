package sdms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;
import sdms.util.UserRoleManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	 @Autowired
	 private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
    	http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
//            	.requestMatchers("/bootstrap/**").permitAll()	// Allow access to static resources like css, js and images
//            	.requestMatchers("/").permitAll() 			// Allow free access to home page
//            	.requestMatchers("/**").permitAll() 
//            	.requestMatchers("/login").permitAll()  	// Allow free access to login page
//            	
            	.requestMatchers( SecurityPaths.getPermittedPaths().toArray( String[] :: new ) ).permitAll()	
            		
            		
            	// .hasRole("")      . hasAnyRole("", "")	
            	.requestMatchers("/dashboard/employee").hasRole( UserRoleManager.ROLE_ADMIN  )
            	.requestMatchers("/dashboard/customer").hasRole( UserRoleManager.ROLE_BASE_CUSTOMER )
                
            	
            	.anyRequest().authenticated()

            )
            .formLogin( form -> form.disable() )	// I use my login 
//            .formLogin(form -> form
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/dashboard/employee", true)
//                    .permitAll()
//             )
//             .logout(logout -> logout
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/login?logout")
//                    .permitAll()
//              )
             .logout( logout -> logout.disable()	// I use my logout
//            		 					.logoutUrl("/logout") 
//            		 					.logoutSuccessUrl("/login?logout") 
//            		 					.invalidateHttpSession(true) 
//            		 					.deleteCookies("JSESSIONID") // Remove any session cookies if used
              )
             .addFilterBefore(jwtAuthenticationFilter, (Class<? extends Filter>) UsernamePasswordAuthenticationFilter.class);  // Add JWT Filter

    	
        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    
}
