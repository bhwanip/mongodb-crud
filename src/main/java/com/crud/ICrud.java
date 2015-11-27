package com.crud;

import java.util.Iterator;

import org.bson.Document;

public interface ICrud<T> {

	void create(T obj);
	Iterator<Document> read(T obj);
	void update(T obj);
	void delete(T obj);

}
