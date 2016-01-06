function addBuilding(draggableElement,droppableElement,springUrl,methodUrl){
	 $(function () {
	    $( draggableElement ).draggable({
	        appendTo: 'body',
	        helper: 'clone',
	    	revert: "invalid"
	    });

	    $( droppableElement ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	          var selectedBuilding = $(ui.draggable).attr("id");
	          var selectedPosition = $(this).attr("id");
	          
	          $( this ).removeClass( droppableElement )
	                   .addClass("building_" + selectedBuilding )
	                   .addClass( 'building');	
	                   	               
	          $( ui.draggable).removeClass("building_" + selectedBuilding )
	                          .removeClass( draggableElement )
	                          .removeClass( 'cursor' );
	                          
	          $('li').find("#"+selectedBuilding).remove();
	          
	          ajaxCallAddBuilding(springUrl+methodUrl,selectedBuilding,selectedPosition);
	        },
	      });
	 });
	 
}