function build(classOfEmptyElement, urls, building, position, playerName, type){
    var typeOfSelectedBuilding = $(building).attr("id");
    var selectedPosition = $(position).attr("id");

    $.ajax({
        type: 'POST',
        url: urls['addBuilding'],
        data: {playerName: playerName, type: typeOfSelectedBuilding, position: selectedPosition},
        success: function (data, textStatus) {
            $("#message").html(data);
            					$( position ).removeClass( classOfEmptyElement )
	                                         .addClass( "building_" + typeOfSelectedBuilding )
	                                         .addClass( 'building' );
            					$( "#"+'clock'+selectedPosition ).addClass( 'clock' );

	                            $( position ).droppable( "disable" );
	                            
	                            
	                            console.log($( "#"+selectedPosition ).attr('class'));
	                            
	                            ajaxCallGetTimeInfo(typeOfSelectedBuilding, 1, selectedPosition, playerName, urls, type);
	                            
	                            $("#"+typeOfSelectedBuilding).remove();
    		   			 }
    	   });
};