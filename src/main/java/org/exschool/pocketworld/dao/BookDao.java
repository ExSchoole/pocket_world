package org.exschool.pocketworld.dao;

import java.util.List;

import org.exschool.pocketworld.model.Book;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDao extends AbstractDAO{

	public Book findOne(long id) {
		Criteria criteria = getSession().createCriteria(Book.class);
		criteria.add(Restrictions.eq("id", id));
		return (Book) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		Criteria criteria = getSession().createCriteria(Book.class);
		return (List<Book>) criteria.list();
	}

	public void create(Book entity) {
		persist(entity);
	}	
}
	