function DragDropBuild(draggableElement,emptyElement,url,playerName){
	 $(function () {
	    $("." + draggableElement ).draggable({
	        appendTo: 'body',
	        helper: 'clone',
	       	revert: "invalid"
	    });

	    $("." + emptyElement ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	                    build(emptyElement,url,ui.draggable,this,playerName);
	        }
	      });
	 });
	 
}