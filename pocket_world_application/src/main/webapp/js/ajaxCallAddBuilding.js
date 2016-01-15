function build(classOfEmptyElement,url, building, position, playerName){
    var typeOfSelectedBuilding = $(building).attr("id");
    var selectedPosition = $(position).attr("id");

    $.ajax({
    		   type: 'POST',
    		   url: url,
    		   data : { playerName: playerName, type: typeOfSelectedBuilding, position: selectedPosition},
    		   success : function(data,textStatus) {
    			   				$("#message").html(data);

                                $( position ).removeClass( classOfEmptyElement )
	                                         .addClass("building_" + typeOfSelectedBuilding )
	                                         .addClass( 'building');

	                            $( position ).droppable( "disable" );

	                            $("#"+typeOfSelectedBuilding).remove();

    			   				console.log(textStatus);
    		   			 }
    	   });
};
