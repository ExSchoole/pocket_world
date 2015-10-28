package org.exschool.pocketworld.service;

import java.util.List;

import org.exschool.pocketworld.model.Book;

public interface BookService {
	
	List<Book> allBooks();
	
	Book get(long id);

	void save(Book entity);
}
