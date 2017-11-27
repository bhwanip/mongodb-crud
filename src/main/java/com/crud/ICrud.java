package com.crud;


public interface ICrud<T> {

	void create(T obj);
	T read(Integer id);
	void update(T obj);
	void delete(T obj);

}
