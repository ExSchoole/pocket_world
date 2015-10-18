package org.exschool.pocketworld.service;

import java.util.List;

import org.exschool.pocketworld.model.Book;

public interface BookService {
	
	List<Book> findAllBooks();
	
	Book findOne(long id);

	void create(Book entity);
}
