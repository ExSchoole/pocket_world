package org.exschool.pocketworld.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class City {
	
	private int id;
	private String name;
	private List<Bilding> bildings;
	private List<Resource> resources;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(targetEntity=Bilding.class,mappedBy="city",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Bilding> getBildings() {
		return bildings;
	}
	public void setBildings(List<Bilding> bildings) {
		this.bildings = bildings;
	}
	@OneToMany(targetEntity=Resource.class,mappedBy="city",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	@Id
	@GeneratedValue
	@Column(name="idPk")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
