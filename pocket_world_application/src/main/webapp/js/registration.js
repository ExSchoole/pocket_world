        function registration(urls){
			$(document).ready(function() {

            $(".signup").click(function(e) {
				e.preventDefault();
                $("fieldset#signup_menu").toggle();
				$(".signup").toggleClass("menu-open");
            });

			$("fieldset#signup_menu").mouseup(function() {
				return false
			});
			$(document).mouseup(function(e) {
				if($(e.target).parent("a.signup").length==0) {
					$(".signup").removeClass("menu-open");
					$("fieldset#signup_menu").hide();
				}
			});
			$('#signup_submit').mouseup(function(event){
				event.preventDefault();
				
				var playerName = $.trim($('#username').val());
				var playerPas = $.trim($('#password').val());
				var cityName = $.trim($('#cityname').val());
				if(playerName!=""&&playerPas!=""&&cityName!=""){
					
					if(playerPas!=$.trim($('#reenter_password').val())){
						alert("Houston, we have a problem");
					} else {
						$.ajax({
				            url: urls['registerNewPlayer'],
				            type: "POST",
				            data : {playerName: playerName, password: playerPas, cityName: cityName},	
				            success: function (data, textStatus) {
				            	if(data=="success"){
				            		document.location.href = "/pocket-world/login";
				            	}else{
				            		$('.registerError').popover('show');
									$('.registerError').on('shown.bs.popover', function () {
										setTimeout(function(){ $('.registerError').popover('hide'); }, 3000);
									   });
				            	}
				            },
				            error: function (xhr, status, errorThrown) {
				                console.log("Error: " + errorThrown);
				            }
				        })
					
					
						
					}
				}
			});

        });
}
			