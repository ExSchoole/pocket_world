function CityCenter(config) {
    var self = this;

    self.build = function(type, positionToBuild, callback) {
        var position = getPositionNumber(positionToBuild.id);
        $.ajax({
            type: 'GET',
            url: config.buildUrl,
            data: {type: type, position: position},
            success: function (data) {
                console.log("SUCCESS");
                positionToBuild.addClass(type)
                callback();
            }
        });
    }
}

function getPositionNumber(positionStr) {
    return positionStr.substring(positionStr.lastIndexOf("_") + 1);
}