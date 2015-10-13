package game.java.dao.ipml;

import org.springframework.stereotype.Repository;

import game.java.dao.ResourcesDAO;
import game.java.models.Resource;

@Repository
public class ResourceDatabaseDAO extends HibernateAbstractDAO<Resource> implements ResourcesDAO {

	public ResourceDatabaseDAO(){}
	
}
