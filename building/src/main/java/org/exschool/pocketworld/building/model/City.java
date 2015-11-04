package org.exschool.pocketworld.building.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Embeddable
public class City {

	
	
	
	
	@GeneratedValue
	@Column(name = "city_id")
	private Long cityId;
	
	private String name;
	
	@Embedded
	@Column(name = "buildings")
	private CityBuildings cityBuildings;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public CityBuildings getCityBuildings() {
		return cityBuildings;
	}
	public void setCityBuildings(CityBuildings cityBuildings) {
		this.cityBuildings = cityBuildings;
	}
	
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	
	
	
	
	
}
