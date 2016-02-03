function CityResources(emptyResourcesClassName, resourcesClassName, urls, playerName, info, typeOfBuilding) {
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

            $.ajax({
                url: urls['buildings'],
                data: JSON.stringify({position: position, type: type, playerName: playerName}),
                contentType: "application/json",
                type: "POST",
                success: function (json) {
                    var resourceBuilding = $("."+emptyResourcesClassName + "#" +position);
                    $( "#"+position ).removeClass(emptyResourcesClassName)
                    				 .addClass("building_" +type)
                    			     .addClass(resourcesClassName);
                    
                    $( "#"+'clock'+position ).addClass( 'clock' );
                    
                    destroyPopover(resourceBuilding);
                    
                    $.each(info, function(index, object){
                    	if (typeOfSelectedBuilding.localeCompare(object['value']['type']) == 0 &&
                    		object['value']['level'] == 1)
                    		timer(position,object['key']*1000, playerName, urls, typeOfBuilding);
		   			});
    
                },
                error: function (xhr, status, errorThrown) {
                    alert("Error occured:" + errorThrown);
                    console.log("Error: " + errorThrown);
                    console.log("Status:" + status);
                    console.dir(xhr);
                }
            })
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