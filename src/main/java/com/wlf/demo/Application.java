package com.wlf.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.wlf.demo.filter.AuthFilter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@Bean  
    public FilterRegistrationBean  filterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        AuthFilter  authFilter  = new AuthFilter ();  
        registrationBean.setFilter(authFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/rest/*");  
        registrationBean.setUrlPatterns(urlPatterns); 
        return registrationBean;  
	} 
	
}
