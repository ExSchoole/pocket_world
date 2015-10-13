package game.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.java.dao.UserDAO;
import game.java.models.User;

@Service(value="userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	@Qualifier(value="userFileDAO")
	private UserDAO userDAO;
	
	public UserService(){}
	
	@Transactional
	public List<User> getAll(){
		return userDAO.getAll();
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user=userDAO.getByUserName(userName);
		if(user==null)
			throw new UsernameNotFoundException("User with UserName:"+userName+" not found");
		return user;
	}

}
