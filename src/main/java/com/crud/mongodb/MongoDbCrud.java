package com.crud.mongodb;

import java.util.Iterator;

import org.bson.Document;

import com.crud.ICrud;
import com.crud.mongodb.dto.User;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDbCrud implements ICrud<User> {

	private final MongoClient mongoClient;
	private final MongoCollection<Document> mongocoll;
	private final MongoDatabase mongoDatabase;

	public MongoDbCrud(){
		mongoClient = new MongoClient("localhost",27017);
		mongoDatabase = mongoClient.getDatabase("gitsamples");
		mongocoll = mongoDatabase.getCollection("users");
	}

	public void create(User user) {
		mongocoll.insertOne(toMongoDocument(user, true));
	}

	public Iterator<Document> read(User user) {
		FindIterable<Document> cursor = mongocoll.find(Filters.eq("_id", user.getId()));
		MongoCursor<Document> iterator = cursor.iterator();
		return iterator;
	}

	public void update(User user) {
		mongocoll.updateMany(Filters.eq("_id", user.getId()), new Document("$set",toMongoDocument(user, false)));
	}

	public void delete(User user) {
		mongocoll.deleteMany(Filters.eq("_id", user.getId()));
	}

	private Document toMongoDocument(User user, boolean includeId) {
		Document document = new Document().append("name", user.getName()).
				append("role", user.getRole()).append("isEmployee", user.isEmployee());
		if(includeId)
			document.append("_id", user.getId());
		return document;
	}

}
