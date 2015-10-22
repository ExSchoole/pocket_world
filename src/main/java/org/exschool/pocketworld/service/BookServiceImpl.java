package org.exschool.pocketworld.service;

import java.util.List;

import org.exschool.pocketworld.dao.BookDao;
import org.exschool.pocketworld.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	public List<Book> allBooks() {
		return bookDao.findAll();
	}
	
	public Book get(final long id) {
	    return bookDao.findOne(id);
	}

	public void save(final Book entity) {
		bookDao.create(entity);
	}
	  
}
