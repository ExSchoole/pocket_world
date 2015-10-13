package game.java.dao.ipml;

import org.springframework.stereotype.Repository;

import game.java.dao.RoleDAO;
import game.java.models.Role;

@Repository(value="roleDatabaseDAO")
public class RoleDatabaseDAO extends HibernateAbstractDAO<Role> implements RoleDAO {
	 
	public RoleDatabaseDAO(){}
	  
}
