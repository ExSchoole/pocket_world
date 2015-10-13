package game.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import game.java.dao.ResourcesDAO;
import game.java.models.Resource;

@Service(value="resourceService")
public class ResourceService {
	@Autowired
	@Qualifier("resourceDatabaseDAO")
	private ResourcesDAO resourcesDAO;
	public ResourceService(){}
	
	public List<Resource> getAll(){
		return resourcesDAO.getAll();
	}
}
