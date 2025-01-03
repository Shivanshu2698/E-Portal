package in.shiva.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {
	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(req -> {
			try {
				req.requestMatchers("/employees")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.oauth2Login();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	return http.build();
	}
}
