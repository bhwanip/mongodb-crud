package com.crud.mongodb;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Iterator;

import org.bson.Document;
import org.junit.Test;

import com.crud.mongodb.dto.User;
import com.sun.istack.internal.logging.Logger;


public class MongoDbCrudTest {

	private final static Logger logger = Logger.getLogger(MongoDbCrudTest.class);
	
	private MongoDbCrud mongoCrud = new MongoDbCrud();

	private User user = new User(){
		{
			setId(999);
			setName("Test User");
			setRole("On Bench");
			setIsEmployee(true);
		}
	};

	@Test
	public void testCreate() {
		mongoCrud.create(user);
		Iterator<Document> results = mongoCrud.read(user);
		Document doc = results.next();
		logger.info("Record is:" + doc);
		assertThat(doc.getInteger("_id"), is(999));
		assertThat(doc.getString("name"), is("Test User"));
		assertThat(doc.getString("role"), is("On Bench"));
		assertThat(doc.getBoolean("isEmployee"),is(true));
		user.setIsEmployee(false);
		mongoCrud.update(user);
		results = mongoCrud.read(user);
		doc = results.next();
		logger.info("Record is:" + doc);
		assertThat(doc.getInteger("_id"), is(999));
		assertThat(doc.getString("name"), is("Test User"));
		assertThat(doc.getString("role"), is("On Bench"));
		assertThat(doc.getBoolean("isEmployee"),is(false));
		mongoCrud.delete(user);
		results = mongoCrud.read(user);
		assertThat(results.hasNext(),is(false));
	}

}
