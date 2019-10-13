package com.psi.persistence;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.psi.models.MyMongoTemplate;

/**
 * 
 * @author kul_grace
 * Generic Mongo DB Operations
 * @param <T>
 *
 */
@Service
@Qualifier("mongoBasicCrud")
public class MongoBasicCrud {
	
	@Autowired
	MyMongoTemplate myMongoTemplate;
	
	/**
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 * 
	 * Retrieve all documents from a collection
	 */
	public <T> List<T> findAll(Class<T> clazz, String collection) {
		MongoTemplate mongoTemplate = myMongoTemplate.mongoTemplate();
		List<T> result =  mongoTemplate.findAll(clazz, collection);
		return result;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param filterQuery (e. g "{ username : { $eq : 'kul_grace' } }")
	 * @param collection
	 * @param clazz
	 * @return 
	 * @return
	 * 
	 * Find documents from a collection
	 */
	public Object find(String filterQuery, String collection, Class<?> clazz) {
		MongoTemplate mongoTemplate = myMongoTemplate.mongoTemplate();
		BasicQuery query = new BasicQuery(filterQuery);
		return  mongoTemplate.findOne(query, clazz, collection);
	}
	
	/**
	 * 
	 * @param object
	 * @param collection
	 * Insert document to a collection
	 */
	public void insert(Object object, String collection) {
		try {
			MongoTemplate mongoTemplate = myMongoTemplate.mongoTemplate();
			mongoTemplate.insert(object, collection);
		} catch(Exception e) {
			throw new PersistenceException("Could not insert data to database");
		}
	}

	/**
	 * 
	 * @param filterQuery
	 * @param collection
	 * @return
	 * 
	 * Delete document from a collection
	 */
	public long remove(String filterQuery, String collection) {
		MongoTemplate mongoTemplate = myMongoTemplate.mongoTemplate();
		BasicQuery query = new BasicQuery(filterQuery);
		DeleteResult result = mongoTemplate.remove(query, collection);
		return result.getDeletedCount();
	}
	
	/**
	 * 
	 * @param filterQuery
	 * @param newValue
	 * @param clazz
	 * @return
	 * 
	 * Update documents from a collection
	 */
	public long update(String filterQuery, Map<String, Object> newValue, Type clazz) {
		MongoTemplate mongoTemplate = myMongoTemplate.mongoTemplate();
		BasicQuery query = new BasicQuery(filterQuery);
		Update update = new Update();
		newValue.forEach((k,v) -> {
			update.set(k, v);
		});
		UpdateResult result = mongoTemplate.updateMulti(query, update, clazz.getClass());
		return result.getModifiedCount();
	}
}
