function timer(position, timeLeft, playerName, urls, type){
	if (timeLeft<=0){
		console.log('end');
		console.log(urls['changeStatus']);
		$( "#"+'clock'+position ).removeClass( 'clock' );
		ajaxCallFinishBuild(playerName, position, urls, type);
		
	}
	else{
		//console.clear();
		console.log(timeLeft/1000);
		setTimeout(function(){timer(position, timeLeft-1000, playerName, urls, type);}, 1000);
	}
}