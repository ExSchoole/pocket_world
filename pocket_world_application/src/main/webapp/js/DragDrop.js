function DragDropBuild(draggableElement,emptyElement,url,playerName,newArr){
	 $(function () {
	    $("." + draggableElement ).draggable({
	        appendTo: 'body',
	        helper: 'clone',
	       	revert: "invalid"
	    });

	    $("." + emptyElement ).droppable({
	    	hoverClass: "over",
	        drop: function( event, ui ) {
	                    build(emptyElement,url,ui.draggable,this,playerName, newArr);
	        },
	      });
	 });
	 
}