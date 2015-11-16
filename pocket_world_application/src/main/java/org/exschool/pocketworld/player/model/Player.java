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


@Entity
@Table(name = "PLAYER")
public class Player {

	

	@Id
	@GeneratedValue
	@Column(name = "player_id")
	private Long playerId;
	
	/*@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="player_resources", joinColumns=@JoinColumn(name="player_id"))*/
	Long resourcesID ;
	

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

	public Long getResourcesID() {
		return resourcesID;
	}

	public void setResourcesID(Long resourcesID) {
		this.resourcesID = resourcesID;
	}

	public Player(Player player) {
		this.login=player.getLogin();
	}
	public Player() {
		this.login="";
	}
	

	
	
	

}
