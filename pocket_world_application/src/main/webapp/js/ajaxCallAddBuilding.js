function build(url, building, position, playerName){
    var selectedBuilding = $(building).attr("id");
    var selectedPosition = $(position).attr("id");

    $.ajax({
    		   type: 'POST',
    		   url: url,
    		   data : { playerName: playerName, type: selectedBuilding, position: selectedPosition},
    		   success : function(data,textStatus) {
    			   				$("#message").html(data);

                                $( position ).removeClass( "building_empty" )
	                                         .addClass("building_" + selectedBuilding )
	                                         .addClass( 'building');

                                $( building ).removeClass("building_" + selectedBuilding )
                                             .removeClass( "building_karusel" )
                                             .removeClass( 'cursor' );

	                            $( position ).droppable( "disable" );

	                            $("#"+selectedBuilding).remove();

    			   				console.log(textStatus);
    		   			 }
    	   });
};
