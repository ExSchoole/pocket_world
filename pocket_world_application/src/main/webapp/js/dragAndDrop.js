function dragDrop(draggableElement,droppableElement,springUrl,methodUrl){
	var selectedBuilding;
	var selectedPosition;
	
	 $(function () {
	    $( draggableElement ).draggable({
	        appendTo: 'body',
	        helper: 'clone',
	    	revert: "invalid"
	    });

	    $( droppableElement ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	          selectedBuilding = $(ui.draggable).attr("id");
	          selectedPosition = $(this).attr("id");
	          
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