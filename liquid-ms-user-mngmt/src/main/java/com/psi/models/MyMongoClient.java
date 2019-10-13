package com.psi.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MyMongoClient
{

	private static Logger logger = LoggerFactory.getLogger(MongoClient.class);
	private static MongoClient instance;

	public static MongoClient getInstance(String ip, Integer port, String username, String password)
	{
		synchronized (MyMongoClient.class)
		{
			if (instance == null)
			{
				//logger.info("Creating Mongo instance");
				String mongoString = "mongodb://" + username + ":" + password + "@" + ip + ":" + port + "/";
				//logger.info(mongoString);
				MongoClientURI uri = new MongoClientURI(mongoString);
				instance = new MongoClient(uri);
				
			}
			return instance;
		}
	}

	public static MongoClient getInstance(String ip, Integer port, String database)
	{
		synchronized (MyMongoClient.class)
		{
			if (instance == null)
			{
				//logger.info("Creating Mongo instance");
				String mongoString = "mongodb://" + ip + ":" + port + "/";
				//logger.info(mongoString);
				MongoClientURI uri = new MongoClientURI(mongoString);
				instance = new MongoClient(uri);
			}
			return instance;
		}
	}

	public void close()
	{
		if (instance != null) {
			try {
				logger.info("Closing Mongo DB connection");
				synchronized (MyMongoClient.class) {
					instance.close();
				}

			} catch (Exception e) {
				logger.error("An error occured while closing the Mongo DB connection");
			}
		} else {
			logger.warn("Mongo object is null. Couldn't close the connection");
		}
	}

}
