function sendMessage(playerName, urls){
	$('#send_btn').click(function(){

		var message = $('#textarea').val();
		var newMessage = messageTemplate(playerName, new Date(), message);
		var allMessages = $('#content').html();
		
		$('#content').html(allMessages + newMessage);

		ajaxCallSendMessage(playerName, 'system', message, urls);
	});
}

function sendSystemMessage(message){
	var newMessage = messageTemplate('System', new Date(), message);
	var allMessages = $('#content').html();
	
	$('#content').html(allMessages + newMessage);
}

function messageTemplate(playerName, date, text){
	var message = '<p>' + text + '</p>'+'<hr>';
	var dateStr = '<span class="small pull-right">' + dateTemplate(date) + '</span>';
	var name = '<h4 class="media-heading">' + playerName + dateStr + '</h4>';
	
	return name + message;
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