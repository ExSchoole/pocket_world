function DragDropBuild(draggableElement,emptyElement,springUrl,methodUrl,playerName){
	 $(function () {
	    $("." + draggableElement ).draggable({
	        appendTo: 'body',
	        helper: 'clone',
	       	revert: "invalid"
	    });

	    $("." + emptyElement ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	          var selectedBuilding = ui.draggable;
	          var selectedPosition = this;

	          build(springUrl+methodUrl,selectedBuilding,selectedPosition,playerName);
	        },
	      });
	 });
	 
}