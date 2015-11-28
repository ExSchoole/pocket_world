   var type = null;
   $(function() {
	    $( ".building_karusel" ).draggable({
	    	revert: "invalid",
	    	start: function() {
	    		type = $(this).attr("id");
	    	}
	    });

	    $( ".building_empty" ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	          $( this ).addClass( type );
	          $( ui.draggable).removeClass( type )
	                          .removeClass( 'building_karusel' )
	                          .removeClass( 'cursor' );
	          $.ajax({          
	    		   type: 'GET',
	    		   url: 'addBuilding',
	    		   data : { type: type, position: $(this).attr("id")  },
	    		   success : function(data) {
	    			   			console.log("SUCCESS");
	    		   			 }
	  		   });
	        },
	      });
	});