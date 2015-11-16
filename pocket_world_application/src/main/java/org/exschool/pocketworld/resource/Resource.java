package org.exschool.pocketworld.resource;

public class Resource {
	  private int gold;
      private int timber;
      private int clay;
      private int corn;
      
    public Resource(int gold, int timber, int clay, int corn){
    	this.gold = gold;
    	this.timber = timber;
    	this.clay = clay;
    	this.corn = corn;  	
    }
      
	public int getGold() {
		return gold;
	}
	public void setGold(int rock) {
		this.gold = rock;
	}
	public int getTimber() {
		return timber;
	}
	public void setTimber(int timber) {
		this.timber = timber;
	}	
	public int getClay() {
		return clay;
	}
	public void setClay(int clay) {
		this.clay = clay;
	}
	public void setCorn(int corn) {
		this.corn = corn;
	}
	public int getCorn() {
		return corn;
	}

}
