package com.example.rqchallenge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableRetry
public class RqChallengeApplication {

	@Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
	
	@Bean  
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
	
    public static void main(String[] args) {
        SpringApplication.run(RqChallengeApplication.class, args);
        
    }

}
