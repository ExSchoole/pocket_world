function ajaxCallGetBuildingQueue(playerName, urls, currentType){	
	$.ajax({
		   type: 'GET',
		   url: urls['getBuildingQueue'],
		   dataType: "json",
		   data : { playerName: playerName },
		   success : function(data) {
			   			console.log(data);
			   			$.each(data, function(index, object){
			   				timer(object['position'], object['time']*1000, playerName,  urls, object['type']);
			   				
			   				if (currentType.localeCompare(object['type']) == 0)
			   					$( "#"+'clock'+object['position'] ).addClass('clock');
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
			   			timer(selectedPosition,data*1000, playerName, urls,globalType);
		   			 }
	   });
	
}