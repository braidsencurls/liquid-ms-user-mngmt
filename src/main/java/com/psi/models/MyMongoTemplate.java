package com.psi.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyMongoTemplate {

	@Value("${mongo.ip}")
	private String host;

	@Value("${mongo.port}")
	private String port;

	@Value("${mongo.username}")
	private String username;

	@Value("${mongo.password}")
	private String password;

	@Value("${mongo.database}")
	private String database;
	
	private static Logger logger = LoggerFactory.getLogger(MyMongoTemplate.class);

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public MongoTemplate mongoTemplate() {
		logger.info("Host: " + host + " Port: " + port + " Username: " + username + " Password: " + "Database: " + database);
		MongoTemplate mongoTemplate = new MongoTemplate(
				MyMongoClient.getInstance(host, new Integer(port), username, password), database);
		return mongoTemplate;

	}

}
