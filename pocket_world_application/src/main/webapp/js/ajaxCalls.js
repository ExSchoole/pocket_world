function ajaxCallGetBuildingQueue(playerName, urls, currentType){	
	$.ajax({
		   type: 'GET',
		   url: urls['getBuildingQueue'],
		   dataType: "json",
		   data : { playerName: playerName },
		   success : function(data) {
			   			console.log(data);
			   			$.each(data, function(index, object){
			   				if (currentType.localeCompare(object['type']) == 0){
			   					$( "#"+'clock'+object['position'] ).addClass('clock');
			   				}
			   				
			   				$( '#' + object['position'] ).addClass( 'timer' );
			   				timer(object['position'], object['time']*1000, playerName, object['level'],  urls, object['type'], object['buildingType']);			   							   	
			   			});
		   			 }
	   });
}

function ajaxCallFinishBuild(playerName, position, urls, type){	
	$.ajax({
		   type: 'GET',
		   url: urls['changeStatus'],
		   dataType: "json",
		   data : { playerName: playerName, position: position, type: type},
		   success : function(data) {
			   			console.log('success');
		   			 }
	   });
}

function ajaxCallGetTimeInfo(type, level, selectedPosition, playerName, urls, globalType){
	$.ajax({
		   type: 'GET',
		   url: urls['timeInfo'],
		   data : {type: type, level: level},
		   success : function(data) {
			   			console.log('success');
			   			console.log(data);
			   			
			   			$( '#' + selectedPosition ).addClass( 'timer' );
			   			timer(selectedPosition,data*1000, playerName, level, urls, globalType, type);
		   			 }
	   });
	
}

function ajaxCallCheckResources(playerName, type, level, urls, emptyElement, position, globalType, boolNewBuilding){	
	$.ajax({
		   type: 'GET',
		   url: urls['checkResources'],
		   data : {playerName: playerName, type: type, level: level},
		   success : function(data) {
			   			console.log(data);
			   		
			   			if (data){ 
			   				if (boolNewBuilding == true){
			   					build(emptyElement, urls, type, position, playerName, globalType);
			   				}
			   				else{ 
			   					ajaxCallLevelUp(urls, type, position, level, playerName, globalType);
			   				}
			   			}
		   			 }
	   });
}


function ajaxCallLevelUp(urls, typeOfSelectedBuilding, position, level, playerName, type){
    $.ajax({
        type: 'POST',
        url: urls['levelUp'],
        data : { playerName: playerName , position: position},
        success: function (data, textStatus) {
        						$("#message").html(data);
        						
            					$( "#"+'clock'+position ).addClass( 'clock' );
	                            
	                            ajaxCallGetTimeInfo(typeOfSelectedBuilding, level, position, playerName, urls, type);
    		   			 }
    	   });
};