  var map = null;
  var stageElevationSelected;  
  
  $(".front-loading").hide();
  $(".front-loading").height($("body").height());
  
  $('#datetimepicker').datetimepicker({
  	dayOfWeekStart : 1,
  	lang:'en'
  	});
  
  $('#modal-elevation').on('shown.bs.modal', function (e) {
	  if(map == null){
    	  map = new GMaps(
    	    {
    	     el: '#map',
    	     lat:47.366009,
    	     lng:9.909770,
    	     zoom:1,
    	     click:function (event) {
    	    	 $('#input-stage-latitude-' + stageElevationSelected).val(event.latLng.lat());
    	    	 $('#input-stage-longitude-' + stageElevationSelected).val(event.latLng.lng());
    	    	 displayLocationElevation(event.latLng, elevator);
    	    }
    	  });
	  }
	})

	var elevator = new google.maps.ElevationService;
  
	function displayLocationElevation(location, elevator) {
		  altitude=0;
		  elevator.getElevationForLocations({
		    'locations': [location]
		  }, function(results, status) {
		    
		    if (status === google.maps.ElevationStatus.OK) {
		      // Retrieve the first result
		      if (results[0]) {
		    	  $('#input-stage-altitude-' + stageElevationSelected).val(Math.round(results[0].elevation * 100) / 100);
		    	  
		      } 
		    }
		    $('#modal-elevation').modal('hide');
		  });
	}
    
    $( document ).ready(function() {
        for(i=0;i<11;i++) {
        	$('#input-stage-kilomiters-' + i).prop('disabled', true);
        	$('#input-stage-altitude-' + i).prop('disabled', true);
        }
    });
    
    function getAltitude(number){
    	
    	$('#modal-elevation-title').text('Click on km '+$('#input-stage-kilomiters-'+number).val() + ' to get elevation');
    	$('#modal-elevation').modal('show');
    	stageElevationSelected=number;
    }
    
    function setIntervalsAltitude(){
    	
    	if($('#input-stage-kilomiters-total').val() != ""){
	    	var delta = $('#input-stage-kilomiters-total').val()/10;
   	        for(i=0;i<11;i++) {  	        	
   	        	$('#input-stage-altitude-' + i).prop('disabled', false);
   	        	$('#input-stage-kilomiters-' + i).val(Math.round(delta * i * 100) / 100);
   	        }
    	}else{
   	        for(i=0;i<11;i++) {  	        	
   	        	$('#input-stage-altitude-' + i).prop('disabled', true);
   	        	$('#input-stage-kilomiters-' + i).val("");
   	        	$('#input-stage-altitude-' + i).val("");
   	        }
    	}
    }
    
    
    function createStage(){
    	if(areCreateFiledsFilled()){
    		
    		var kilomitersArray = [];
        	var elevationArray = [];
        	var longitudeArray = [];
        	var latitudeArray = [];
        	
            for(i=0;i<11;i++) {  	        	
            	kilomitersArray.push($('#input-stage-kilomiters-' + i).val());
            	elevationArray.push($('#input-stage-altitude-' + i).val());
            	longitudeArray.push($('#input-stage-longitude-' + i).val());
            	latitudeArray.push($('#input-stage-latitude-' + i).val());
            }    	   
       		$.ajax({
       		    url : "/stages/create",
       		    type: "POST",
       		    data : {name: $("#input-stage-name").val(),
       		    	    teamId: $("#input-stage-team").val(),
    	   		    	date: $("#datetimepicker").val(),
    	   		    	kilomitersTotal: $("#input-stage-kilomiters-total").val(),
    	   		    	kilomiters: kilomitersArray.toString(),
    	   		    	elevation: elevationArray.toString(),
    	   		    	longitude: longitudeArray.toString(),
    	   		    	latitude: latitudeArray.toString()},
    	   		beforeSend: function(){$('.front-loading').show();},
    	   		error: function(){$('.front-loading').hide();},
       		    success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
       		});	
    	}
    }
    
    function areCreateFiledsFilled(){
    	var result = true;
        for(i=0;i<11;i++) {  	
        	result = validateField('input-stage-altitude-' + i, result);
        	result = validateField('input-stage-kilomiters-' + i, result);
        }
	    
	    result = validateField('input-stage-name', result);
	    result = validateField('datetimepicker', result);
	    result = validateField('input-stage-kilomiters-total', result);
	    result = validateField('input-stage-team', result);

	    return result;
    }
	
	 $(function () {
			
			guidely.add ({
				attachTo: '#div-menu'
				, anchor: 'top-left'
				, title: 'Guide 1/7'
				, text: 'This is the menu, use it to travel through the platform'
			});
			
			guidely.add ({
				attachTo: '#div-new-stage'
				, anchor: 'top-left'
				, title: 'Guide 2/7'
				, text: 'Once you have joinned a team, you will be able to create a stage!'
			});
			
			guidely.add ({
				attachTo: '#div-stage-name'
				, anchor: 'top-left'
				, title: 'Guide 3/7'
				, text: 'a. Set a stage name.'
			});
			
			guidely.add ({
				attachTo: '#div-stage-team'
				, anchor: 'top-left'
				, title: 'Guide 4/7'
				, text: 'b. Choose the team which will do it.'
			});
			
			guidely.add ({
				attachTo: '#div-stage-kilomiters-total'
				, anchor: 'top-left'
				, title: 'Guide 5/7'
				, text: 'c. Set the total amount of kilomiters.'
			});
			
			guidely.add ({
				attachTo: '#div-stage-datetimepicker'
				, anchor: 'top-left'
				, title: 'Guide 6/7'
				, text: 'd. Choose date and time.'
			});
			
			guidely.add ({
				attachTo: '#div-stage-altitudes'
				, anchor: 'top-left'
				, title: 'Guide 7/7'
				, text: 'e. Select the exact position into the map in order to get the altitude.'
			});
			
			guidely.init ({ welcome: false, startTrigger: true, showOnStart:false });
		});