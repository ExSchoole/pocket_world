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

function timers(buildingTypeToShow) {

    var self = this;

    self.loadTimers = function() {
        return $.get("#springUrl('/build/timers')");
    };

    self.addClocks = function(timers) {
        _.each(timers, function (timer) {
            $("#" + timer.position).addClass('clock');
        })
    };

    self.startTimers = function(timers) {

    };
    $.when(self.loadTimers()).then(function (timers) {
        self.startTimers(timers);
        var timersWithCurrentBuildings = _.filter(timers, new function(timer) {
            return timer.type == buildingTypeToShow;
        });
        self.addClocks(timersWithCurrentBuildings)
    })
}