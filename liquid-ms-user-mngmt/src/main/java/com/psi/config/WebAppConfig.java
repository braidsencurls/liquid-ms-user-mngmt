package com.psi.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mongodb.MongoClient;

@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {"com.psi"})
@PropertySource("classpath:application.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter
{
	private AppConfig appConfig = null;

	@Autowired
	public ApplicationContext applicationContext;

/*	@Value("${dbIsAuthenticationRequired}")
	private boolean isAuthenticationRequired;

	@Value("${dbHost}")
	private String host;

	@Value("${dbPort}")
	private int port;

	@Value("${dbUser}")
	private String dbUser;

	@Value("${dbPassword}")
	private String dbPassword;*/

	//private MongoClient mongoClient;

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		//registry.addInterceptor(new ApiAuthInterceptor()).addPathPatterns("/api/*");
		super.addInterceptors(registry);
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer)
	{
		super.configureAsyncSupport(configurer);
	}

	@Bean
	public InternalResourceViewResolver jstlViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setOrder(0);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public AppConfig getAppConfig()
	{

		if (appConfig == null)
		{
			try
			{
				PropertyLoader propertyLoader = new PropertyLoader();
				propertyLoader.fetchProperties();
				appConfig = propertyLoader.getAppConfig();
			}
			catch(IOException ioexception)
			{
				System.out.println(ioexception.getMessage());
			}
		}
		return appConfig;
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	/*@Bean
	public MongoClient getMongoClient()
	{
		try
		{
			if (isAuthenticationRequired)
			{
				MongoCredential mongoCredential = MongoCredential.createCredential(dbUser, "admin", dbPassword.toCharArray());
				mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(mongoCredential));
				return mongoClient;
			}
			else
			{
				mongoClient = new MongoClient(host, port);
				return mongoClient;
			}
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		return null;
	}*/
}