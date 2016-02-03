function timer(position, timeLeft, playerName, type) {
    if (timeLeft <= 0) {
        console.log('end');
        $("#" + 'clock' + position).removeClass('clock');
        ajaxCallFinishBuild(playerName, position, type);

    }
    else {
        //console.clear();
        console.log(timeLeft / 1000);
        setTimeout(function () {
            timer(position, timeLeft - 1000, playerName, type);
        }, 1000);
    }
}
//temporary, rename
function newTimer() {

    this.loadTimers = function () {}

}

function timers(buildingTypeToShow) {

    var self = this;

    self.loadTimers = function() {
        return $.get("#springUrl('/build/timers')");
    };

    self.
    $.when(self.loadTimers()).then(function (timers) {

    })
}