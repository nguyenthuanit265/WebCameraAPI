package com.myclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class WebCameraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCameraApiApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer configurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("PUT", "GET", "POST", "DELETE")
//						.allowCredentials(false).maxAge(3600);
//			}
//		};
//	}

}
