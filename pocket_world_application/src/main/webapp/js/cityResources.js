function CityResources(emptyResourcesClassName, resourcesClassName, addResourcesUrl, loadTypesUrl) {

    var availableTypes = [];
    fillAvailableTypes();
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    $(function () {
        $("."+emptyResourcesClassName).click(function (event) {
            event.preventDefault();
            var div = $(this);
            var position = $(this).attr('id');
            hideAllPopovers();
            showPopover(div, "Build", generateListOfLinks(availableTypes, position))
        });

        $("div.container").on("click", "a.popover_item", function (event) {
            event.preventDefault();
            var position = $(this).attr('position');
            var type = $(this).attr('type');

            $.ajax({
                url: addResourcesUrl,
                data: JSON.stringify({position: position, type: type}),
                contentType: "application/json",
                type: "POST",
                success: function (json) {
                    var resourceBuilding = $("."+emptyResourcesClassName + "#" +position);
                    resourceBuilding.removeClass(emptyResourcesClassName).addClass("building_" +type).addClass(resourcesClassName);
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
                availableTypes = availableTypes.concat(types)
            },
            error: function (xhr, status, errorThrown) {
                console.log("Error: " + errorThrown);
            }
        })
    }

}