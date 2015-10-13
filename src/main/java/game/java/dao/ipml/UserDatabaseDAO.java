package game.java.dao.ipml;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import game.java.dao.UserDAO;
import game.java.models.User;
@Repository("userDatabaseDAO")
public class UserDatabaseDAO extends HibernateAbstractDAO<User> implements UserDAO{

	public UserDatabaseDAO(){}

	@Override
	public User getByUserName(String userName) {
		Criteria criteria= getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (User)criteria.uniqueResult();
	}
	

}
