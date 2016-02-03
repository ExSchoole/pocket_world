function build(classOfEmptyElement, urls, building, position, playerName, info, type){
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
	                            
	                            $.each(info, function(index, object){
	                            	if (typeOfSelectedBuilding.localeCompare(object['value']['type']) == 0 &&
	                            		object['value']['level'] == 1)
	                            		timer(selectedPosition,object['key']*1000, playerName, urls, type);
	    			   			});
	                            
	                            $("#"+typeOfSelectedBuilding).remove();
    		   			 }
    	   });
};