package game.java.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="resorces")
public class Resource extends Model {


	private static final long serialVersionUID = 7760001231574422364L;
	@Column(name="title",length=20)
	private String title;
	@Column(name="description",length=50)
	private String description;
	@Column(name="count",length=5)
	private int count;
	@ManyToOne
	private ResourceCategory resourceCategory;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ResourceCategory getResourceCategory() {
		return resourceCategory;
	}
	public void setResourceCategory(ResourceCategory resourceCategory) {
		this.resourceCategory = resourceCategory;
	}
	public Resource(){super();};
	public Resource(Long id){super(id);};
}
