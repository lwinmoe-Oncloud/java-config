package com.hello.service.dao.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hello.service.dao.controller")
public class WebMvcConfig implements WebMvcConfigurer {
	public void addInterceptors(InterceptorRegistry registry) {

	}

	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/customer").setViewName("customer");
		registry.addViewController("/admin").setViewName("admin");
		registry.addViewController("/member").setViewName("member");
		registry.addViewController("/address").setViewName("address");

	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}

	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub

		registry.jsp().prefix("/view/").suffix(".jsp");
	}

}
