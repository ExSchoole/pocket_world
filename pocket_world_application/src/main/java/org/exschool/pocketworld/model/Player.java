package org.exschool.pocketworld.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Player {
	@Id
	private String login;
	//private Long password;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cityFk")
	private City city;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	/*public Long getPassword() {
		return password;
	}
	public void setPassword(Long password) {
		this.password = password;
	}*/
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}
