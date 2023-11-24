package com.hello.service.dao.configs;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationLoader extends AbstractAnnotationConfigDispatcherServletInitializer{
	
@Override
protected Class<?>[] getServletConfigClasses() {
	// TODO Auto-generated method stub
	return new Class<?>[]{
		WebMvcConfig.class,AdminSecurityConfig.class
	};
}
@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

}
