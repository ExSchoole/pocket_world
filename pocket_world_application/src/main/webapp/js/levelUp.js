 function levelUpBuilding(urls,playerName,template,globalType){
      
	 $( ".building" ).click(function() {

    	  console.log($(this).attr("id"));
    	  
          var $div = $(this);
          $.ajax({
               url: urls['getInfo'],
               dataType:'json',
               type: "GET",
               contentType: "application/json; charset=utf-8",
               data : { playerName: playerName , position: $(this).attr("id")},
                         success : function(info) {
            	   			 console.log(info);
                        	 showPopoverInfo($div, urls, playerName, info, template, globalType)
                         }
               })     
      });
      
      $( "body" ).mouseup(function() {
    	  console.log("back");
    	  hideAllPopovers();
      });   
}

function showPopoverInfo(element,urls,playerName,info,template,globalType){	
	element.popover({
        title: "Upgrade",
        content: template(info),
        placement: top,
        trigger: "focus",
        html: true
    }).on('shown.bs.popover', function () {
        var $popup = $(this);
        $(this).next('.popover').find('button.lvlUp').click(function (e) {
        	ajaxCallCheckResources(playerName, info['type'], info['level']+1, urls, "", element.attr("id"), globalType, false);  
            $popup.popover('hide');
        
        });

    }).popover('show');
}