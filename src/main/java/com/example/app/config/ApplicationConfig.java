package com.example.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.app.filter.AuthFilter;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
	
	@Override
	public Validator getValidator() {
	var validator = new LocalValidatorFactoryBean();
	validator.setValidationMessageSource(messageSource());
	return validator;
	}
	@Bean
	MessageSource messageSource() {
	var messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("validation");
	return messageSource;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
		.addResourceLocations("file///Users/mukawachirei/uploads/");
//		.addResourceLocations("file:///C:/Users/zd2N09/uploads/");
	}

	@Bean
	 FilterRegistrationBean<AuthFilter> authFilter() {
	 var bean =
	new FilterRegistrationBean<AuthFilter>(new AuthFilter());
	 bean.addUrlPatterns("/recipe/*", "/admin/*");

	 return bean;
	 } 
}
