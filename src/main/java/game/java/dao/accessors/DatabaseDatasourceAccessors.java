package game.java.dao.accessors;

import javax.sql.DataSource;

public abstract class DatabaseDatasourceAccessors {
 
	public abstract String getHost();
	
	public abstract int getPort();
	
	public DataSource load()
	{
		return null;
	}
}
