package org.exschool.pocketworld.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.exschool.pocketworld.model.Book;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl extends AbstractJpaDAO<Book> implements BookDao{
	
	public BookDaoImpl() {
		super();
		
		setClazz(Book.class);
	}

}
	