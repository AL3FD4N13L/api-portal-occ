package br.com.picpay.mscontacts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://10.137.146.150", "http://portal-occ.ppay.me/", "http://localhost:3000") 
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
  
}
	
}
