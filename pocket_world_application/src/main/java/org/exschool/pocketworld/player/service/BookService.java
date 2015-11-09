package org.exschool.pocketworld.player.service;

import java.util.List;

import org.exschool.pocketworld.player.model.Book;



public interface BookService {
	
	List<Book> allBooks();
	
	Book get(long id);

	List<Book> getByTitle(String title);
	
	void save(Book entity);
}
