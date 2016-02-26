function CityResources(emptyResourcesClassName, resourcesClassName, urls, playerName, globalType) {
    var availableTypes = [];
    fillAvailableTypes();
    var variable;

    $(function () {
        $("."+emptyResourcesClassName).click(function (event) {
            event.preventDefault();
            var div = $(this);
            var position = $(this).attr('id');
            variable = $(this);
            hideAllPopovers();
            showPopover(div, "Build", generateListOfLinks(availableTypes, position))
        });

        $("div.container").on("click", "a.popover_item", function (event) {
        	if ($(this).hasClass('timer')==false){
            event.preventDefault();
            var position = $(this).attr('position');
            var typeOfBuilding = $(this).attr('type');
            var level = 1;

            $.ajax({
     		   type: 'GET',
     		   url: urls['checkResources'],
     		   data : {playerName: playerName, type: typeOfBuilding, level: level},
     		   success : function(data) {
     			   			console.log(data);
     			   
     			   			if (data) 
     			   				buildResourceBuilding(playerName, typeOfBuilding, urls, emptyResourcesClassName, position, resourcesClassName, globalType);
     		   			 }
     	   				});
        	}
        	});
    });
    function generateListOfLinks(elements, position){
        var result = '';
        $.each(elements, function(index, element){
            result+="<a href='#' class='popover_item' position="+position+" type="+element+">"+element+"</a><br>" ;
        });
        //console.log(result);
        return result;
    }
    function fillAvailableTypes() {
        $.ajax({
            url: urls['types'],
            dataType: 'json',
            type: "GET",
            success: function (types) {
                availableTypes = availableTypes.concat(types)
            },
            error: function (xhr, status, errorThrown) {
                console.log("Error: " + errorThrown);
            }
        })
    }

}

function buildResourceBuilding(playerName, typeOfBuilding, urls, emptyResourcesClassName, position, resourcesClassName, globalType){
	$.ajax({
        url: urls['buildings'],
        data: JSON.stringify({position: position, type: typeOfBuilding}),
        contentType: "application/json",
        type: "POST",
        success: function (json) {
            var resourceBuilding = $("."+emptyResourcesClassName + "#" +position);
            $( "#"+position ).removeClass(emptyResourcesClassName)
            				 .addClass("building_" +typeOfBuilding)
            			     .addClass(resourcesClassName);
            
            $( "#"+'clock'+position ).addClass( 'clock' );
            
            destroyPopover(resourceBuilding);
            
            ajaxCallGetTimeInfo(playerName, typeOfBuilding, 1, urls, position, globalType);

        },
        error: function (xhr, status, errorThrown) {
            alert("Error occured:" + errorThrown);
            console.log("Error: " + errorThrown);
            console.log("Status:" + status);
            console.dir(xhr);
        }
    })
}