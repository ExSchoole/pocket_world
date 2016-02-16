function DragDropBuild(draggableElement,emptyElement,urls,playerName, type){
	 $(function () {
	    $("." + draggableElement ).draggable({
	        appendTo: 'body',
	        helper: 'clone',
	       	revert: "invalid"
	    });

	    $("." + emptyElement ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	        			ajaxCallCheckResources(playerName, $(ui.draggable).attr("id"), 1, urls, emptyElement, $(this).attr("id"), type);
	        },
	      });
	 });
	 
}