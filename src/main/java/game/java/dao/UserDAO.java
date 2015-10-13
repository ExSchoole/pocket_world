package game.java.dao;

import game.java.models.User;

public interface UserDAO extends ItemDAO<User> {
	public User getByUserName (String userName);
}
