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