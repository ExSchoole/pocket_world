function sendMessage(playerName, urls, msg_template){
	$('#send_btn').click(function(){

		var message = $('#textarea').val();
		var recipientName = $('#recipientName').val();

		ajaxCallSendMessage(playerName, recipientName, message, urls, msg_template);
	});
}

function dateTemplate(date){
	var day = date.getDate();
		if (day<10){
			day = '0'+day;
		}
	var month = date.getMonth();
		if (month<10){ 
			month = '0'+month;
		}
	return date.getHours()+':'+date.getMinutes()+' '+day+'.'+month+'.'+date.getFullYear();
}