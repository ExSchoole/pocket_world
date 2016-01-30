function build(classOfEmptyElement,url, building, position, playerName, newArr){
    var typeOfSelectedBuilding = $(building).attr("id");
    var selectedPosition = $(position).attr("id");

    $.ajax({
        type: 'POST',
        url: url,
        data: {playerName: playerName, type: typeOfSelectedBuilding, position: selectedPosition},
        success: function (data, textStatus) {
            $("#message").html(data);
                                $( position ).removeClass( classOfEmptyElement )
	                                         .addClass( "building_" + typeOfSelectedBuilding )
	                                         .addClass( 'building' )
	                                         .addClass( 'clock' );

	                            $( position ).droppable( "disable" );
	                            	                         
	                            var k = 0;
	                            while (typeOfSelectedBuilding.localeCompare(newArr[k].type) != 0 || newArr[k].level != 1){
	                            	k++;
	                            }
	                            
	                            $("#"+typeOfSelectedBuilding).remove();
    			   				
    			   				//timer(position,newArr[k].time*1000);
    		   			 }
    	   });
};