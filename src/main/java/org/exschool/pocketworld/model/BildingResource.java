package org.exschool.pocketworld.model;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
public class BildingResource {

	private Bilding bilding;
	private Long Id;
	protected String resourceType;
	protected int count;
	private int level;
	private BildingsList bildingType;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public BildingsList getBildingType() {
		return bildingType;
	}
	public void setBildingType(BildingsList bildingType) {
		this.bildingType = bildingType;
	}
	
	@Id
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	@ManyToOne
	@JoinColumn(name="bilding")
	public Bilding getBilding() {
		return bilding;
	}
	public void setBilding(Bilding bilding) {
		this.bilding = bilding;
	}

	
	
	
}
