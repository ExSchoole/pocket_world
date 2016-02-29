        function registration(url){
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
			$('#signup_submit').click(function(event){
				event.preventDefault();
				  
				var userLog = $.trim($('#username').val());
				var userPas = $.trim($('#password').val());
				var cityName = $.trim($('#cityname').val());
				if(userPas!=$.trim($('#reenter_password').val())){
					alert("Houston, we have a problem");
				} else {
					alert(url);
					$.ajax({
						type: 'POST',
						url: url,
						data: {playerName : userLog, password: userPas, cityName:cityName},
						error: function(req, text, error) {
							alert('Error AJAX: ' + text + ' | ' + error);
						},
						success: function (data) {
							alert(data);
							if(data=="Success!"){
								document.location.replace("/pocket-world/login");
							}
						}
					});	
				}
			});

        });
}
			