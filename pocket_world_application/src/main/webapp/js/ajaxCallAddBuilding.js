function ajaxCallAddBuilding(url,type,position) {
	$.ajax({          
		   type: 'POST',
		   url: url,
		   data : { type: type, position: position},
		   success : function(data) {
			   			console.log("SUCCESS");
		   			 }
	   });
	};
