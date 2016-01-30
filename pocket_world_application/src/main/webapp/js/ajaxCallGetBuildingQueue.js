function ajaxCallGetBuildingQueue(playerName, url){
	
	
	/*$('.building_empty')
	$(function () {
		$('.building_empty').each(function(){
	      console.log('ok');
	      console.log($(this).attr('id'));
		});
	});*/
	
	$.ajax({
		   type: 'GET',
		   url: url,
		   dataType: "json",
		   data : { playerName: playerName },
		   success : function(data) {
			   			var position;
			   			$.each(data, function(key, value){
			   				    position = $('.building_'+key).attr('id');
			   					console.log(position, value*1000);
			   					$( '#'+position ).addClass('clock');
			   					timer(key, value*1000);
			   			});
		   			 }
	   });
}