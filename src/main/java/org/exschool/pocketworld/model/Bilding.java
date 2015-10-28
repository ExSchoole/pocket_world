package org.exschool.pocketworld.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bilding {
	
	private City city;
	private BildingsList bildingType;
	private int level;
	private List<BildingResource> bildingResouces;
	private List<Production> productions;
	private Time time;
	
	@Id
	public BildingsList getBildingType() {
		return bildingType;
	}
	public void setBildingType(BildingsList bildingType) {
		this.bildingType = bildingType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@OneToMany(targetEntity=BildingResource.class,mappedBy="bilding",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<BildingResource> getBildingResouces() {
		return bildingResouces;
	}
	public void setBildingResouces(List<BildingResource> bildingResouces) {
		this.bildingResouces = bildingResouces;
	}
	
	@OneToMany(targetEntity=Production.class,mappedBy="bilding",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Production> getProductions() {
		return productions;
	}
	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="time")
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	@ManyToOne
	@JoinColumn(name="city")
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}
