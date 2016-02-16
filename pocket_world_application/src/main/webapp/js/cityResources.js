function CityResources(emptyResourcesClassName, resourcesClassName, urls, playerName, typeOfBuilding) {
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
            event.preventDefault();
            var position = $(this).attr('position');
            var type = $(this).attr('type');
            var level = 1;

            $.ajax({
     		   type: 'GET',
     		   url: urls['checkResources'],
     		   data : {playerName: playerName, type: type, level: level},
     		   success : function(data) {
     			   			console.log(data);
     			   
     			   			if (data) 
     			   				buildResourceBuilding(type, position, playerName, urls, typeOfBuilding, emptyResourcesClassName, resourcesClassName);
     		   			 }
     	   });
            
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

function buildResourceBuilding(type, position, playerName, urls, typeOfBuilding, emptyResourcesClassName, resourcesClassName){
	$.ajax({
        url: urls['buildings'],
        data: JSON.stringify({position: position, type: type}),
        contentType: "application/json",
        type: "POST",
        success: function (json) {
            var resourceBuilding = $("."+emptyResourcesClassName + "#" +position);
            $( "#"+position ).removeClass(emptyResourcesClassName)
            				 .addClass("building_" +type)
            			     .addClass(resourcesClassName);
            
            $( "#"+'clock'+position ).addClass( 'clock' );
            
            destroyPopover(resourceBuilding);
            
            ajaxCallGetTimeInfo(type, 1, position, playerName, urls,  typeOfBuilding);

        },
        error: function (xhr, status, errorThrown) {
            alert("Error occured:" + errorThrown);
            console.log("Error: " + errorThrown);
            console.log("Status:" + status);
            console.dir(xhr);
        }
    })
}