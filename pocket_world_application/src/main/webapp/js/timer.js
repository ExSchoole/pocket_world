function timer(playerName, typeOfBuilding, level,  urls, position, timeLeft, globalType, typeofBuildingMatchGlobalType){
	if (timeLeft<=0){
		console.log('end '+ position);
		
		if (typeofBuildingMatchGlobalType == true){
			$( '#' + position ).removeClass( 'timer' );
			$( "#"+'clock'+position ).removeClass( 'clock' );
			$( "#"+'level'+position ).removeClass( 'level' + level-1 );
			$( "#"+'level'+position ).addClass( 'level' + level );
		}
		
		ajaxCallFinishBuild(playerName, position, urls, globalType);
		$("#"+'timer'+position+globalType).text("");
	}
	else{
		$("#"+'timer'+position+globalType).text(typeOfBuilding+" - "+convertTime(timeLeft/1000));
		setTimeout(function(){
						timer(playerName, typeOfBuilding, level, urls, position, timeLeft-1000, globalType, typeofBuildingMatchGlobalType);
					}, 1000);
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