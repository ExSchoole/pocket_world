package org.exschool.pocketworld.building.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Time {
	
	@EmbeddedId
	private TimePK timePK;

	private int time;

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	
	
	
	
}
