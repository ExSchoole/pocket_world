function sendMessage(playerName, urls, msg_template){
	$('#send_btn').click(function(){

		var message = $('#textarea').val().trim();
		var recipientName = $('#selecteduser').text().trim();

        if (message == ''){
            alert("Message is empty");
        } else {
            if (recipientName == ''){
                alert("Login is empty");
            } else {
                ajaxCallSendMessage(playerName, recipientName, message, urls, msg_template);
            }
        }
	});
}

function chooseUser(playerName, urls, msg_template){
    var chosenUserName;
    $('.users').click(function(){
        chosenUserName = $(this).text();
        $("#selecteduser").html(chosenUserName+" <span class='caret'></span>");
        ajaxCallGetAllMessagesBetweenTwoUsers(playerName, chosenUserName, urls, msg_template);
    });
}

function addUser(playerName, urls, relation_template, msg_template){
    $('#add_user').click(function(){
        ajaxCallGetAllUsers(urls);
    });

    $('#btn_add_user').click(function(){
        ajaxCallAddUser(playerName, $('#search_user').val().trim(), urls, relation_template, msg_template);
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
	var minute = date.getMinutes();
	    if (minute<10){
	        minute = '0'+minute;
	    }

	return date.getHours()+':'+minute+' '+day+'.'+month+'.'+date.getFullYear();
}