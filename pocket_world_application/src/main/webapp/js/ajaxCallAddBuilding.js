function build(classOfEmptyElement, urls, typeOfSelectedBuilding, selectedPosition, playerName, type){
    $.ajax({
        type: 'POST',
        url: urls['addBuilding'],
        data: {playerName: playerName, type: typeOfSelectedBuilding, position: selectedPosition},
        success: function (data, textStatus) {
        						$("#message").html(data);
            					$('#' + selectedPosition ).removeClass( classOfEmptyElement )
	                                         .addClass( "building_" + typeOfSelectedBuilding )
	                                         .addClass( 'building' );
            					$( "#"+'clock'+selectedPosition ).addClass( 'clock' );

	                            $('#' + selectedPosition ).droppable( "disable" );
	                            
	                            ajaxCallGetTimeInfo(typeOfSelectedBuilding, 1, selectedPosition, playerName, urls, type);
	                            
	                            $("#"+typeOfSelectedBuilding).remove();
    		   			 }
    	   });
};