function timer(position, timeLeft, playerName, urls, globalType, typeOfBuilding){
	if (timeLeft<=0){
		console.log('end');
		console.log(urls['changeStatus']);
		$( "#"+'clock'+position ).removeClass( 'clock' );
		ajaxCallFinishBuild(playerName, position, urls, globalType, typeOfBuilding);
		$("#"+'timer'+position+globalType).text("");
	}
	else{
		$("#"+'timer'+position+globalType).text(typeOfBuilding+" - "+convertTime(timeLeft/1000));
		console.log(convertTime(timeLeft/1000));
		setTimeout(function(){timer(position, timeLeft-1000, playerName, urls, globalType, typeOfBuilding);}, 1000);
	}
}

function convertTime(time){
	var timeFormat;
	var h = Math.floor(time/3600);
	time = time-h*3600;
	
	if (h<10 && h>0) timeFormat = '0'+h+':';
	else
		if (h==0) timeFormat = '00:';
		else timeFormat = h+':'
	
	var m = Math.floor(time/60);
	time = time-m*60;
	if (m<10 && m>0) timeFormat=timeFormat+'0'+m+':';
	else
		if (m==0) timeFormat=timeFormat+'00:';
		else timeFormat=timeFormat+m+":";
	
	var s = time;
	if (time<10) timeFormat=timeFormat+'0'+s;
	else timeFormat=timeFormat+s;
	
	return timeFormat;
}