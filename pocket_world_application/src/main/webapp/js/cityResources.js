function CityResources(emptyResourcesSelector, resourcesSelector, addResourcesUrl, loadTypesUrl) {

    var availableTypes = [];
    fillAvailableTypes();

    $(function () {
        $(emptyResourcesSelector).click(function (event) {
            event.preventDefault();
            var div = $(this);
            var position = $(this).attr('id');
            hideAllPopovers();
            showPopover(div, "Build", generateListOfLinks(availableTypes, position))
        });

        $("div.container .popover_item").on("click", function (event) {
            event.preventDefault();
            var position = $(this).attr('position');
            var type = $(this).attr('type');

            $.ajax({
                url: addResourcesUrl,
                contentType: "application/json",
                type: "POST",
                success: function (json) {
                    var resourceBuilding = $(emptyResourcesSelector + "#" +position);
                    resourceBuilding.removeClass(emptyResourcesSelector).addClass("res_building_" +type).addClass(resourcesSelector);
                    destroyPopover(resourceBuilding);
                },
                error: function (xhr, status, errorThrown) {
                    alert("Error occured:" + errorThrown);
                    console.log("Error: " + errorThrown);
                    console.log("Status:" + status);
                    console.dir(xhr);
                }
            })
        });

        $("body").on('click', function () {

        })
    });


    function generateListOfLinks(elements, position){
        var result = '';
        $.each(elements, function(index, element){
            result+="<a href='#' class='popover_item' position="+position+" type="+element+">"+element+"</a><br>" ;
        });
        console.log(result);
        return result;
    }
    function fillAvailableTypes() {
        $.ajax({
            url: loadTypesUrl,
            dataType: 'json',
            type: "GET",
            success: function (types) {
                availableTypes = types.concat(types)
            },
            error: function (xhr, status, errorThrown) {
                console.log("Error: " + errorThrown);
            }
        })
    }

}