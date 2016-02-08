 function levelUpBuilding(url,playerName,template){
      $( ".building" ).click(function() {

          var $div = $(this);
          
          var id_name = $(this).attr('id');
          var $position = id_name.substr('square_'.length,id_name.length);
          $.ajax({
               url: url+"/getInfo",
               dataType:'json',
               type: "GET",
               contentType: "application/json; charset=utf-8",
               data : { playerName: playerName , position: $(this).attr("id")},
                         success : function(info) {
                            showPopover($div,url,playerName,info,template)
                         }

               })     
      });
      
      $( "body" ).click(function() {
    	  $(".popover").each(function(index){
              $(this).popover('hide');
          })
      });

     
}

function showPopover(element,url,playerName,info,template){

var contentHtml = [
'<div>',
	'<h5>Time: '+info[0].value+' </h5>',
	'<h5>Clay: '+info[1].value+'</h5>',
	'<h5>Corn: '+info[2].value+'</h5>',
    '<h5>Gold: '+info[3].value+'</h5>',
    '<h5>Timber: '+info[4].value+'</h5>',
    
    '<button class="btn btn-primary lvlUp">Level Up</button>',
'</div>'].join('\n');

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