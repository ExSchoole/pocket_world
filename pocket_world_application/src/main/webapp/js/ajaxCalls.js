function ajaxCallGetBuildingQueue(playerName, currentType, otherType){
	$.ajax({
		   type: 'GET',
		   url: DICTIONARY.urls.getBuildingQueue,
		   dataType: "json",
		   data : { playerName: playerName },
		   success : function(data) {
			   			console.log(data);
			   			$.each(data[currentType], function(position, time){
			   				console.log(time);
			   				$( "#"+'clock'+position ).addClass('clock');
			   				timer(position, time*1000, playerName, currentType);
			   			});
			   			
			   			$.each(data[otherType], function(position, time){
		   					console.log(time);
		   					timer(position, time*1000, playerName, otherType);
		   			});
		   			 }
	   });
}

function ajaxCallFinishBuild(playerName, position, type){
	$.ajax({
		   type: 'GET',
		   url: DICTIONARY.urls.changeStatus,
		   dataType: "json",
		   data : { playerName: playerName, position: position, type: type},
		   success : function(data) {
			   			console.log('success');
		   			 }
	   });
}