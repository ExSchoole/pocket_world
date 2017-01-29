package org.exschool.pocketworld.service;

import java.util.List;

import org.exschool.pocketworld.model.Book;

public  abstract interface Service<T> {
	
	List<T> all();
	
	T get(long id);

	List<T> getByTitle(String title);
	
	void save(T entity);

}
