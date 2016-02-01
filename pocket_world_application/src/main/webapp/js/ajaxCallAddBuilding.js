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
	                            var k = 0;
	                            while (typeOfSelectedBuilding.localeCompare(info[k].type) != 0 || info[k].level != 1){
	                            	k++;
	                            }
	                            
	                            $("#"+typeOfSelectedBuilding).remove();

    			   				timer(selectedPosition,info[k].time*1000, playerName, urls, type);
    		   			 }
    	   });
};