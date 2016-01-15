package org.exschool.pocketworld.info.building;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.exschool.pocketworld.building.model.BuildingType;


public interface BuildingInfoService {
	 List<BuildingInfo> allBuildings();
	 Collection<BuildingInfo> saveAll();
	 
	 List<BuildingInfo> BUILDINGS_INFO = Arrays.asList(
			 
			  new BuildingInfo(BuildingType.BARN,1,5,1,2,3,4),
		      new BuildingInfo(BuildingType.BARN,2,10,5,6,7,8),
			  new BuildingInfo(BuildingType.BARN,3,15,9,10,11,12),

			  new BuildingInfo(BuildingType.FARM,1,5,1,2,4,3),
		      new BuildingInfo(BuildingType.FARM,2,10,5,6,8,7),
			  new BuildingInfo(BuildingType.FARM,3,15,9,10,12,11),

			  new BuildingInfo(BuildingType.GILOTHOME,1,5,1,3,2,4),
		      new BuildingInfo(BuildingType.GILOTHOME,2,10,5,7,6,8),
			  new BuildingInfo(BuildingType.GILOTHOME,3,15,9,11,10,12),
			  
			  new BuildingInfo(BuildingType.MALL,1,5,1,3,4,2),
		      new BuildingInfo(BuildingType.MALL,2,10,5,7,8,6),
			  new BuildingInfo(BuildingType.MALL,3,15,9,11,12,10),
			  
			  new BuildingInfo(BuildingType.MARKETPLACE,1,5,1,4,2,3),
		      new BuildingInfo(BuildingType.MARKETPLACE,2,10,5,8,6,7),
			  new BuildingInfo(BuildingType.MARKETPLACE,3,15,9,12,10,11),
			  
			  new BuildingInfo(BuildingType.PLANT,1,5,1,4,3,2),
		      new BuildingInfo(BuildingType.PLANT,2,10,5,8,7,6),
			  new BuildingInfo(BuildingType.PLANT,3,15,9,12,11,10),
			  
			  new BuildingInfo(BuildingType.POOL,1,5,2,1,3,4),
		      new BuildingInfo(BuildingType.POOL,2,10,6,5,7,8),
			  new BuildingInfo(BuildingType.POOL,3,15,10,9,11,12),
			  
			  new BuildingInfo(BuildingType.SCHOOL,1,5,3,1,2,4),
		      new BuildingInfo(BuildingType.SCHOOL,2,10,7,5,6,8),
			  new BuildingInfo(BuildingType.SCHOOL,3,15,11,9,10,12),
			  
			  new BuildingInfo(BuildingType.STORAGE,1,5,4,1,2,3),
		      new BuildingInfo(BuildingType.STORAGE,2,10,8,5,6,7),
			  new BuildingInfo(BuildingType.STORAGE,3,15,12,9,10,11),
			 
			  new BuildingInfo(BuildingType.TOWNHALL,1,5,4,3,2,1),
		      new BuildingInfo(BuildingType.TOWNHALL,2,10,8,7,6,5),
			  new BuildingInfo(BuildingType.TOWNHALL,3,15,12,11,10,9)
			 
			 );
}
