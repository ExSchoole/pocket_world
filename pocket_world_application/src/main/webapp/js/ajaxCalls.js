function ajaxCallGetBuildingQueue(playerName, urls, currentType, otherType){	
	$.ajax({
		   type: 'GET',
		   url: urls['getBuildingQueue'],
		   dataType: "json",
		   data : { playerName: playerName },
		   success : function(data) {
			   			console.log(data);
			   			$.each(data[currentType], function(index, object){
			   				console.log(object['position']);
			   				console.log(object['time']);
			   				timer(object['position'], object['time']*1000, playerName,  urls, otherType);
			   			});
			   			
			   			$.each(data[otherType], function(index, object){		
		   					timer(object['position'], object['time']*1000, playerName,  urls, otherType);
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