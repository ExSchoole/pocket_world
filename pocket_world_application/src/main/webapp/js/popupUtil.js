function showPopover(element, title, content){
    element.popover({
        title: title,
        content: content,
        placement: top,
        trigger: "focus",
        html: true
    }).popover('show');
}

function destroyPopover(element){
    element.popover('destroy');
}

function hideAllPopovers(){
    $(".popover").each(function(index){
        $(this).popover('hide');
    })
}

function destroyAllPopovers(){
    $(".popover").each(function(index){
        $(this).popover('destroy');
    })
}