package game.java.dao.ipml;

import org.springframework.stereotype.Repository;

import game.java.dao.ResourceCategoryDAO;
import game.java.models.ResourceCategory;

@Repository(value="resourceCategoryDatabseDAO")
public class ResourceCategoryDatabaseDAO extends HibernateAbstractDAO<ResourceCategory> implements ResourceCategoryDAO {

	public ResourceCategoryDatabaseDAO(){}
	
}
