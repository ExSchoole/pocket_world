package org.exschool.pocketworld.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Production {
	
	private Bilding bilding;
	private String poductionType;
	private int count;
	private int level;
	
	@Id
	public String getPoductionType() {
		return poductionType;
	}
	public void setPoductionType(String poductionType) {
		this.poductionType = poductionType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
