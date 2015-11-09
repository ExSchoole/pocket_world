package org.exschool.pocketworld.player.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.exschool.pocketworld.building.model.City;
import org.exschool.pocketworld.resource.model.Resource;

@Entity
@Table(name = "PLAYER")
public class Player {

	

	@Id
	@GeneratedValue
	@Column(name = "player_id")
	private Long playerId;
	

	//private Long password;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cityFk")
	private City city;
	
	@Embedded
	/*@Column(name = "resource_balance")
	private ResourceBalance resourceBalance;*/
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="player_resources", joinColumns=@JoinColumn(name="player_id"))
	List<Resource> resources = new ArrayList<>();
	

	@Column(name = "login")
	private String login;

	public Long getPlayerId() {
		return playerId;
	}

	public String getLogin() {
		return login;
	}


	public void setPlayerId(Long id) {
		this.playerId = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	@Override
	public String toString() {
		return "Player [resources=" + resources + "]";
	}
	
	

}
