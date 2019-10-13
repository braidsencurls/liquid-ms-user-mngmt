package com.psi.persistence.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.psi.models.MyMongoTemplate;
import com.psi.models.User;
import com.psi.persistence.MongoBasicCrud;

public class MongoBasicCrudTest {

	private final String MONGO_IP = "10.9.80.185";
	private final String MONGO_PORT = "27017";
	private final String MONGO_USERNAME = "admin";
	private final String MONGO_PASSWORD = "core_ee";
	private final String MONGO_DATABASE = "admin";

	@Before
	public void setup() {
		template = spy(new MyMongoTemplate());
		/*MyMongoTemplate.setHost(MONGO_IP);
		MyMongoTemplate.setPort(MONGO_PORT);
		MyMongoTemplate.setUsername(MONGO_USERNAME);
		MyMongoTemplate.setPassword(MONGO_PASSWORD);
		MyMongoTemplate.setDatabase(MONGO_DATABASE);*/
	}

	MongoBasicCrud basicCrud = new MongoBasicCrud();
	MyMongoTemplate template = new MyMongoTemplate();

	@Test
	public void findAllTest() {
		List<User> users = (List<User>) basicCrud.findAll(User.class, "users");
		System.out.println(users.toString());
		assert (users.size() > 0);
	}

	@Test
	public void findTest() {
		User user = (User) basicCrud.find("{username : {$eq : 'chris0330' } }", "users", User.class);
		assertNotNull(user);
	}

	@Test
	public void insertUserTest() {
		User user = new User();
		user.setFirstname("Chinnasamy");
		user.setEmail("chinnasamy@psi-incontrol.com");
		user.setMobile("018-97329274");
		user.setUsername("kul_samy");

		basicCrud.insert(user, "users");

		User newUser = (User) basicCrud.find("{ username : { $eq : 'kul_samy' } }", "users", User.class);
		assertNotNull(newUser);
	}

	@Test
	public void removeTest() {
		long result = basicCrud.remove("{'username':'kul_samy'}", "users");
		System.out.println("Result: " + result);
		assert (result > 0);
	}

	@Test
	public void updateTest() {
		Map<String, Object> newValue = new HashMap<String, Object>();
		newValue.put("mobile", "9999999");
		long result = basicCrud.update("{'username':'chris0330'}", newValue, User.class);
		System.out.println("Results: " + result);
		assert (result > 0);
	}

}
