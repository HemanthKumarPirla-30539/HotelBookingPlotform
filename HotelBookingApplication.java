package com.example.HotelBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HotelBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("http://localhost:5177")
	                    .allowedMethods("*");
	        }
	    };
	}
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	            .allowedOrigins("https://your-frontend-app.netlify.app") // Change this to your Netlify URL
	            .allowedMethods("GET", "POST", "PUT", "DELETE");
	}

}
