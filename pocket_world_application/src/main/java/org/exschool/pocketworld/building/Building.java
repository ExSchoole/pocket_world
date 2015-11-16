package org.exschool.pocketworld.building;

public class Building {
	
	private int pos;
	private String type;
	
	public Building(int position, String TypeOfBuilding){
		pos = position;
		type = TypeOfBuilding;
	}
	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	/*public Map<Integer,String> builds;
	
	public Building(){
		     
		   	 Random r = new Random();
		   	 builds = new HashMap<Integer,String>();
		        for (int i=0; i<12; i++)
		   		builds.put(r.nextInt(12)+1, findHouse(r.nextInt(4)+1));
	}*/

}
