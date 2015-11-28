   var selectedPosition = null;
   var typeOfBuilding = null;
   var karuselCols = document.querySelectorAll('.building_karusel');
   var cityCols = document.querySelectorAll('.building_empty');

   [].forEach.call(karuselCols, function (kcol) {
	   kcol.addEventListener('dragstart', handleDragStartKarusel, false);
	   kcol.addEventListener('dragend', handleDragEndKarusel, false);
    });
        
   [].forEach.call(cityCols, function (ccol) {
	   ccol.addEventListener('dragenter', handleDragEnterCity, false); 
	   ccol.addEventListener('dragleave', handleDragLeaveCity, false);
	   ccol.addEventListener('dragover', handleDragOverCity, false);
       ccol.addEventListener('drop', handleDropCity, false);
    });

    function handleDragStartKarusel(e) {               
       typeOfBuilding = this.classList[0];
    }
     
   function handleDragEnterCity(e){
       this.classList.add('over');
       selectedPosition = this;
    }
    
    function handleDragLeaveCity(e) {
       this.classList.remove('over');
       selectedPosition = null;
    }
    
    function handleDragOverCity(e){
       if (e.preventDefault) {
           e.preventDefault(); 
       }
    }
    
    function handleDropCity(e){
       if (e.stopPropagation) {
           e.stopPropagation(); 
       }
       
       selectedPosition = this;
    }
    
    function handleDragEndKarusel(e) {                
       if (selectedPosition != null) {
    	   this.draggable = false;
    	   this.removeEventListener('dragstart', handleDragStartKarusel, false);
    	   this.removeEventListener('dragend', handleDragEndKarusel, false); 
    	   this.classList.remove(typeOfBuilding);
    	   this.classList.remove('cursor'); 
         
    	   selectedPosition.classList.remove('over');
    	   selectedPosition.classList.add(typeOfBuilding);
    	   selectedPosition.classList.add('building_position');
    	   selectedPosition.classList.add('building');
           
    	   selectedPosition.removeEventListener('dragenter', handleDragEnterCity, false); 
    	   selectedPosition.removeEventListener('dragleave', handleDragLeaveCity, false);
    	   selectedPosition.removeEventListener('dragover', handleDragOverCity, false);
    	   selectedPosition.removeEventListener('drop', handleDropCity, false);
           
    	   $.ajax({          
    		   type: 'GET',
    		   url: 'addBuilding',
    		   data : { type: typeOfBuilding, position: selectedPosition.id  },
    		   success : function(data) {
    			   			console.log("SUCCESS");
    		   			 }
  		   });
       }
       
    }