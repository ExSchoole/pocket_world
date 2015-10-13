package game.java.dao;
import java.util.List;

import game.java.models.*;
public interface ItemDAO<T extends Model> {

		public List<T> getAll();
		
		public T getById(Long id);
		
		public void add(T model);
		
		public void update(T model);
		
		public void remove(T model);
		

}			
