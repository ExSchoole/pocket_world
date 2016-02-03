function CityResources(emptyResourcesClassName, resourcesClassName, playerName, info, typeOfBuilding) {
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
                url: DICTIONARY.urls.buildings,
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
                    console.log($( "#"+position ).attr('class'));
                    var k = 0;
                    while (type.localeCompare(info[k].type) != 0 || info[k].level != 1){
                    	k++;
                    }
                    console.log(info[k].type,info[k].level,info[k].time);
                    timer(position,info[k].time*1000, playerName, typeOfBuilding);
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
        $.when($.get(DICTIONARY.urls.types)).then(function (types) {
            availableTypes = availableTypes.concat(types);
        });
    }

}