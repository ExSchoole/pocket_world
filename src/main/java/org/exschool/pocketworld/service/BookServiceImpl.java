package org.exschool.pocketworld.service;

import java.util.List;

import org.exschool.pocketworld.dao.BookDao;
import org.exschool.pocketworld.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	public List<Book> findAllBooks() {
		return bookDao.findAll();
	}
	
	public Book findOne(final long id) {
	    return bookDao.findOne(id);
	}

	@Transactional
	public void create(final Book entity) {
		bookDao.create(entity);
	}
	  
}
