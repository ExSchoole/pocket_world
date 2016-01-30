function timer(position, timeLeft){
	if (timeLeft<=0){
		console.log('end');
		$( position ).removeClass( 'clock' );
	}
	else{
		console.clear();
		console.log(timeLeft/1000);
		setTimeout(function(){timer(position, timeLeft-1000);}, 1000);
	}
}