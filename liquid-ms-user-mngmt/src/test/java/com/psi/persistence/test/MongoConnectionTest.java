package com.psi.persistence.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.psi.models.MyMongoTemplate;

public class MongoConnectionTest {

	private final String MONGO_IP = "10.9.80.185";
	private final String MONGO_PORT = "27017";
	private final String MONGO_USERNAME = "admin";
	private final String MONGO_PASSWORD = "core_ee";
	private final String MONGO_DATABASE = "admin";

	MyMongoTemplate template = new MyMongoTemplate();

	@Before
	public void setup() {
		template = spy(new MyMongoTemplate());
		/*MyMongoTemplate.setHost(MONGO_IP);
		MyMongoTemplate.setPort(MONGO_PORT);
		MyMongoTemplate.setUsername(MONGO_USERNAME);
		MyMongoTemplate.setPassword(MONGO_PASSWORD);
		MyMongoTemplate.setDatabase(MONGO_DATABASE);*/
	}

	@Test
	public void getMongoTemplate() {
		MongoTemplate mongoTemplate = template.mongoTemplate();
		assertNotNull(mongoTemplate);
	}

}
