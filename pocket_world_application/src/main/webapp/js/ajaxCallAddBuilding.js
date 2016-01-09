function ajaxCallAddBuilding(url,type,position,playerName) {
	$.ajax({          
		   type: 'POST',
		   url: url,
		   data : { playerName: playerName, type: type, position: position},
		   success : function(data,textStatus) {			   
			   				$("#message").html(data);
			   				console.log(textStatus);
		   			 }
	   });
	};
