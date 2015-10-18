package org.exschool.pocketworld.dao;

import java.util.List;

import org.exschool.pocketworld.model.Book;

public interface BookDao {
	Book findOne(long id);
	List<Book> findAll();
	void create(Book entity);
}
