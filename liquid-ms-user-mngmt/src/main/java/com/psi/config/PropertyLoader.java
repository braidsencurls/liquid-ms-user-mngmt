package com.psi.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mrprintedwall on 12/25/16.
 */
public class PropertyLoader
{
	private AppConfig appConfig;

	public void fetchProperties() throws IOException
	{
		Properties properties = new Properties();
		String propertyFile = "application.properties";
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile))
		{
			if(inputStream == null)
			{
				System.out.println("The application.properties cannot be found in classpath");
			}
			properties.load(inputStream);
			appConfig = new AppConfig();
			appConfig.setAdministratorPassword(properties.getProperty("administratorPassword"));
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
	}

	public AppConfig getAppConfig()
	{
		return appConfig;
	}
}