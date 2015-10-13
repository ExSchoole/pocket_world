package game.java.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="resource_category")
public class ResourceCategory extends Model {

	private static final long serialVersionUID = 7425595368867226882L;
	@Column(name="title",length=255)
	private String title;
	@OneToMany(mappedBy="resourceCategory", cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Resource> products=new HashSet<>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ResourceCategory(){super();};
	public ResourceCategory(Long id){super(id);};
	 
}
