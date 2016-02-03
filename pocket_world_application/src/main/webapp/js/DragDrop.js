function initBuildingsDragDrop(draggableElement, emptyElement, playerName, info, type) {
    $(function () {
        $("." + draggableElement).draggable({
            appendTo: 'body',
            helper: 'clone',
            revert: "invalid"
        });

        $("." + emptyElement).droppable({
            hoverClass: "over",
            drop: function (event, ui) {
                build(emptyElement, ui.draggable, this, playerName, info, type);
            }
        });
    });

}