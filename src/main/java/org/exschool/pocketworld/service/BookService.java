package org.exschool.pocketworld.service;

import java.util.List;

import org.exschool.pocketworld.model.Book;

public interface BookService {
	
	List<Book> allBooks();
	
	Book get(long id);

	List<Book> getByTitle(String title);
	
	void save(Book entity);
}
