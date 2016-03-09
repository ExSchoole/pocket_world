function ajaxCallGetBuildingQueue(urls, playerName, globalType){	
	var typeofBuildingMatchGlobalType;
	
	$.ajax({
		   type: 'GET',
		   url: urls['getBuildingQueue'],
		   dataType: "json",
		   data : { playerName: playerName },
		   success : function(data) {
			   			console.log(data);
			   			$.each(data, function(index, object){
			   				if (globalType.localeCompare(object['type']) == 0){
			   					$( "#"+'clock'+object['position'] ).addClass('clock');
			   					$( '#' + object['position'] ).addClass( 'timer' );
			   					typeofBuildingMatchGlobalType = true;
			   				}else{
			   					typeofBuildingMatchGlobalType = false;
			   				}
			   				
		   	
			   				timer(playerName, object['buildingType'], object['level'], urls, object['position'], object['time']*1000, object['type'], typeofBuildingMatchGlobalType);			   							   	
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

function ajaxCallGetTimeInfo(playerName, typeOfBuilding, level, urls, position, globalType){
	$.ajax({
		   type: 'GET',
		   url: urls['timeInfo'],
		   data : {type: typeOfBuilding, level: level},
		   success : function(data) {
			   			console.log('success');
			   			console.log(data);
			   			
			   			$( '#' + position ).addClass( 'timer' );
			   			timer(playerName, typeOfBuilding, level, urls, position, data*1000, globalType, true);
		   			 }
	   });
	
}

function ajaxCallCheckResources(playerName, typeOfBuilding, level, urls, emptyElement, position, globalType, boolNewBuilding){	
	$.ajax({
		   type: 'GET',
		   url: urls['checkResources'],
		   data : {playerName: playerName, type: typeOfBuilding, level: level},
		   success : function(data) {
			   			console.log(data);
			   		
			   			if (data){ 
			   				if (boolNewBuilding == true){
			   					build(playerName, typeOfBuilding, urls, emptyElement, position, globalType);
			   				}
			   				else{ 
			   					ajaxCallLevelUp(playerName, typeOfBuilding, level, urls, position, globalType);
			   				}
			   			}
		   			 }
	   });
}


function ajaxCallLevelUp(playerName, typeOfBuilding, level, urls, position, globalType){
    $.ajax({
        type: 'POST',
        url: urls['levelUp'],
        data : { playerName: playerName , position: position},
        success: function (data, textStatus) {
        						$("#message").html(data);
        						
            					$( "#"+'clock'+position ).addClass( 'clock' );
	                            
	                            ajaxCallGetTimeInfo(playerName, typeOfBuilding, level, urls, position, globalType);
    		   			 }
    	   });
};

function ajaxCallSendMessage(playerName, recipientName, message, urls){
    $.ajax({
        type: 'GET',
        url: urls['sendMessage'],
        data : { sender: playerName, recipient: recipientName, message: message},
        success: function (data, textStatus) {
                            console.log(data);
    		   			 }
    	   });
};