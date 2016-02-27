function build(playerName, typeOfBuilding, urls, classOfEmptyElement, selectedPosition, globalType){
	$.ajax({
        type: 'POST',
        url: urls['addBuilding'],
        data: {playerName: playerName, type: typeOfBuilding, position: selectedPosition},
        success: function (data, textStatus) {
        						$("#message").html(data);
            					$('#' + selectedPosition ).removeClass( classOfEmptyElement )
	                                         .addClass( "building_" + typeOfBuilding )
	                                         .addClass( 'building' )
            								 .addClass( 'timer' );
            					
            					$( "#"+'clock'+selectedPosition ).addClass( 'clock' );

	                            $('#' + selectedPosition ).droppable( "disable" );
	                            
	                            ajaxCallGetTimeInfo(playerName, typeOfBuilding, 1, urls, selectedPosition, globalType, true);
	                            
	                            $("#"+typeOfBuilding).remove();
    		   			 }
    	   });
};