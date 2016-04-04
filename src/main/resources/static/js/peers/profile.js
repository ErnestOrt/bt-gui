  $(".front-loading").hide();
  $(".front-loading").height($("body").height());
  
  function createTeam(){
    	if(validateField('input-team-name', true)){
    		$('#modal-create-team').modal('hide');
	   		$.ajax({
	   		    url : "/profile/createteam",
	   		    type: "POST",
	   		    data : {name: $("#input-team-name").val()},
	   		 	beforeSend: function(){$('.front-loading').show();},
		   		error: function(){$('.front-loading').hide();},
			    success: function(data, textStatus, jqXHR) { location.reload();}
	   		});	
    	}
    }
    
    function unjoinTeam(teamIdValue){
   		$.ajax({
   		    url : "/profile/unjointeam",
   		    type: "POST",
   		    data : {teamId: teamIdValue},
   		    beforeSend: function(){$('.front-loading').show();},
	   		error: function(){$('.front-loading').hide();},
		    success: function(data, textStatus, jqXHR) { location.reload();}
   		});	
    }
    
    function joinTeam(){
    	if(validateField('input-team-code', true)){
    		$('#modal-join-team').modal('hide');
	   		$.ajax({
	   		    url : "/profile/jointeam",
	   		    type: "POST",
	   		    data : {teamId: $("#input-team-code").val()},
	   		 	beforeSend: function(){$('.front-loading').show();},
		   		error: function(){$('.front-loading').hide();},
			    success: function(data, textStatus, jqXHR) { location.reload();}
	   		});	
    	}
    }
    
    function deleteBikes(){
    	var bikes = [];
    	
    	$('input[name=input-delete-bike]:checked').each(function(){
    		bikes.push($(this).attr("value"));
    	});
    	if(bikes.length > 0){
    		$('#modal-remove-bike').modal('hide');
    		$.ajax({
    		    url : "/profile/deltebikes",
    		    type: "POST",
    		    data : {ids: bikes.toString()},
    		    beforeSend: function(){$('.front-loading').show();},
		   		error: function(){$('.front-loading').hide();},
			    success: function(data, textStatus, jqXHR) { location.reload();}
    		});	
    	}
    }
    
    function savePersonalInformation(){
    	var iconAvatar = $('input[name=input-avatar]').filter(':checked').val();
    	if(iconAvatar == null){
    		iconAvatar='undefined';
    	}
    	if(validateField('input-name', true)){
    		$('#modal-personal-information').modal('hide');
    		$.ajax({
    		    url : "/profile/savepersonalinformation",
    		    type: "POST",
    		    data : {name: $("#input-name").val(), description: $("#input-description").val(), icon: iconAvatar},
    		    beforeSend: function(){$('.front-loading').show();},
		   		error: function(){$('.front-loading').hide();},
			    success: function(data, textStatus, jqXHR) { location.reload();}
    		});
    	}
    }
    
    function saveSkills(){
    	$('#modal-skills').modal('hide');
   		$.ajax({
   		    url : "/profile/saveskills",
   		    type: "POST",
   		    data : {resistence: $("#input-resistence").val(),
	   		    	sprint: $("#input-sprint").val(),
	   		    	montain: $("#input-montain").val(),
	   		    	flat: $("#input-flat").val(),
	   		    	btt: $("#input-btt").val(),
   		    		road: $("#input-road").val()},
 		   	beforeSend: function(){$('.front-loading').show();},
 			error: function(){$('.front-loading').hide();},
 			success: function(data, textStatus, jqXHR) { location.reload();}
   		});
    }
    
    function addBike(){
    	if(validateField('input-bike-name', true)){
    		$('#modal-add-bike').modal('hide');
    		$.ajax({
       		    url : "/profile/addbike",
       		    type: "POST",
       		    data : {name: $("#input-bike-name").val()},
       		 	beforeSend: function(){$('.front-loading').show();},
  				error: function(){$('.front-loading').hide();},
  				success: function(data, textStatus, jqXHR) { location.reload();}
       		});
    	}
    }
    
    function guide(menu, teams, personal, bikes, skills, joined) {
    	
    	guidely.add ({attachTo: '#div-menu', anchor: 'top-left', title: 'Guide 1/6', text: menu});
    	guidely.add ({attachTo: '#div-teams', anchor: 'top-left', title: 'Guide 2/6', text: teams});
    	guidely.add ({attachTo: '#div-personal', anchor: 'top-left', title: 'Guide 3/6', text: personal});
    	guidely.add ({attachTo: '#div-bikes', anchor: 'top-left', title: 'Guide 4/6', text: bikes});
    	guidely.add ({attachTo: '#div-skills', anchor: 'top-left', title: 'Guide 5/6', text: skills});
    	guidely.add ({attachTo: '#div-joined', anchor: 'top-left', title: 'Guide 6/6', text: joined});
    	
    	guidely.init ({welcome: false, startTrigger: true, showOnStart:false});	
    }
    