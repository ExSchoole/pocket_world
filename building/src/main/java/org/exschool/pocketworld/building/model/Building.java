package org.exschool.pocketworld.building.model;

import javax.persistence.*;

@Entity
@Table(name="Building")
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private BuildingType buildingType;
	
	
	private int level;

	private int position;

	private Long cityId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public BuildingType getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Building building = (Building) o;

		if (level != building.level) return false;
		if (position != building.position) return false;
		if (buildingType != building.buildingType) return false;
		if (cityId != null ? !cityId.equals(building.cityId) : building.cityId != null) return false;
		if (id != null ? !id.equals(building.id) : building.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (buildingType != null ? buildingType.hashCode() : 0);
		result = 31 * result + level;
		result = 31 * result + position;
		result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
		return result;
	}
}
