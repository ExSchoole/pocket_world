function ajaxCallGetBuildingQueue(playerName, urls, currentType, otherType){	
	$.ajax({
		   type: 'GET',
		   url: urls['getBuildingQueue'],
		   dataType: "json",
		   data : { playerName: playerName },
		   success : function(data) {
			   			var position;
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