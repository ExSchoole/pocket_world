package org.exschool.pocketworld.player.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.exschool.pocketworld.building.model.City;
import org.exschool.pocketworld.resource.model.ResourceBalance;



@Entity
@Table(name = "PLAYER")
public class Player {

	@Id
	@GeneratedValue
	@Column(name = "player_id")
	private Long playerId;
	
	@Embedded
	@Column(name = "city")
	private City city;

	
	@Embedded
	@Column(name = "resource_balance")
	private ResourceBalance resourceBalance;

	@Column(name = "login")
	private String login;

	public Long getPlayerId() {
		return playerId;
	}

	public String getLogin() {
		return login;
	}

	public ResourceBalance getResourceBalance() {
		return resourceBalance;
	}

	public void setPlayerId(Long id) {
		this.playerId = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setResourceBalance(ResourceBalance resourceBalance) {
		this.resourceBalance = resourceBalance;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", resourceBalance="
				+ resourceBalance + ", login=" + login + "]";
	}

	/*public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}*/
	
	

}
