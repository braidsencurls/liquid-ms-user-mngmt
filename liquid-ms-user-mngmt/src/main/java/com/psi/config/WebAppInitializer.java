package com.psi.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer
{
	private static final String CONFIG_LOCATION = "com.psi.config";

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException
	{
		System.out.println("");

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.setConfigLocation(CONFIG_LOCATION);
		applicationContext.setServletContext(servletContext);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(
				applicationContext);
		//servletContext.addListener(new SessionListener()); //Uncomment this for SessionListener
		ServletRegistration.Dynamic servlet = servletContext.addServlet(
				"servlet-dispatcher", dispatcherServlet);

		servlet.setLoadOnStartup(1);
		servlet.setAsyncSupported(true);
		servlet.addMapping("/");
	}
}
