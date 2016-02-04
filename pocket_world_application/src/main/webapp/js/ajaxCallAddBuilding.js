function build(classOfEmptyElement, url, building, position, playerName) {
    var typeOfSelectedBuilding = $(building).attr("id");
    var selectedPosition = $(position).attr("id");
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    $.ajax({
        type: 'POST',
        url: url,
        data: {playerName: playerName, type: typeOfSelectedBuilding, position: selectedPosition},
        success: function (data, textStatus) {
            $("#message").html(data);

            $(position).removeClass(classOfEmptyElement)
                .addClass("building_" + typeOfSelectedBuilding)
                .addClass('building');

            $(position).droppable("disable");

            $("#" + typeOfSelectedBuilding).remove();

            console.log(textStatus);
        }
    });
}
