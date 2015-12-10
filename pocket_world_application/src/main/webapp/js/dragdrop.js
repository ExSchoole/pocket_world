function dragDrop(config,type,position) {
	$.ajax({          
		   type: 'POST',
		   url: config.buildUrl,
		   data : { type: type, position: position},
		   success : function(data) {
			   			console.log("SUCCESS");
		   			 }
	   });
	};
