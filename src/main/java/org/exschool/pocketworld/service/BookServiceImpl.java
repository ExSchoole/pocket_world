package org.exschool.pocketworld.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private Dao dao;
	
	public List<Book> allBooks() {
		return dao.all(Book.class);
	}
	
	public Book get(final long id) {
	    return dao.get(Book.class,id);
	}

	public void save(final Book entity) {
		dao.save(entity);
	}

	@Override
	public List<Book> getByTitle(String title) {
		Map<String,String> map = new HashMap<>();
		map.put("title",title);
		return dao.getAllBy(Book.class,map);  
	}
}
