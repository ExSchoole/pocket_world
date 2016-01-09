function DragDropInitializationAjaxCall(draggableElement,emptyElement,springUrl,methodUrl,playerName){
	 $(function () {
	    $("." + draggableElement ).draggable({
	        appendTo: 'body',
	        helper: 'clone',
	       	revert: "invalid"
	    });

	    $("." + emptyElement ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	          var selectedBuilding = $(ui.draggable).attr("id");
	          var selectedPosition = $(this).attr("id");
	          
	          $( this ).removeClass( emptyElement )
	                   .addClass("building_" + selectedBuilding )
	                   .addClass( 'building');	
	                   	               
	          $( ui.draggable).removeClass("building_" + selectedBuilding )
	                          .removeClass( draggableElement )
	                          .removeClass( 'cursor' );
	                          
	          $("#"+selectedBuilding).remove();
	          
	          ajaxCallAddBuilding(springUrl+methodUrl,selectedBuilding,selectedPosition,playerName);
	        },
	      });
	 });
	 
}