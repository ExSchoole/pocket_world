 function levelUpBuilding(url,playerName,template){
      $( ".building" ).click(function() {

          var $div = $(this);
          $.ajax({
               url: url+"/getInfo",
               dataType:'json',
               type: "GET",
               contentType: "application/json; charset=utf-8",
               data : { playerName: playerName , position: $(this).attr("id")},
                         success : function(info) {
            	   			showPopoverInfo($div,url,playerName,info,template)
                         }
               })     
      });
      
      $( "body" ).mouseup(function() {
    	  hideAllPopovers();
      });   
}

function showPopoverInfo(element,url,playerName,info,template){

    element.popover({
        title: "Upgrade",
        content: template(info),
        placement: top,
        trigger: "focus",
        html: true
    }).on('shown.bs.popover', function () {
        var $popup = $(this);
        $(this).next('.popover').find('button.lvlUp').click(function (e) {
                  
               ajaxLvlUp(url,playerName,element.attr("id"));    
            $popup.popover('hide');
        });

    }).popover('show');
}

function ajaxLvlUp(url,playerName,id){
$.ajax({
           type: 'POST',
           url: url+"/levelUp",
           data : { playerName: playerName , position: id},
           success : function(data,textStatus) {
                            $("#message").html(data);
                            console.log(textStatus);
                     }
           });          
}