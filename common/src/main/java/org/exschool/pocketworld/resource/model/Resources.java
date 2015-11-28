package org.exschool.pocketworld.resource.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resources {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resources_id")
	private Long id;
	@Embedded
	private Resource gold;
	@Embedded
	private Resource timber;
	@Embedded
	private Resource clay;
	@Embedded
	private Resource corn;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Resource getGold() {
		return gold;
	}
	public void setGold(Resource gold) {
		this.gold = gold;
	}
	public Resource getTimber() {
		return timber;
	}
	public void setTimber(Resource timber) {
		this.timber = timber;
	}
	public Resource getClay() {
		return clay;
	}
	public void setClay(Resource clay) {
		this.clay = clay;
	}
	public Resource getCorn() {
		return corn;
	}
	public void setCorn(Resource corn) {
		this.corn = corn;
	}

	
}
