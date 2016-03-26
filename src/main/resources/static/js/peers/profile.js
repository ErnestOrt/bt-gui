	function createTeam(){
    	if(validateField('input-team-name', true)){
    		$('#modal-create-team').modal('hide');
	   		$.ajax({
	   		    url : "/profile/createteam",
	   		    type: "POST",
	   		    data : {name: $("#input-team-name").val()},
	   		 	beforeSend: function(){$('.front-loading').show();},
		   		error: function(){$('.front-loading').hide();},
			    success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
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
		    success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
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
			    success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
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
			    success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
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
			    success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
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
 			success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
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
  				success: function(data, textStatus, jqXHR) { setTimeout(function(){ location.reload(); }, 3000);}
       		});
    	}
    }
    
$(function () {
	
	$(".front-loading").hide();
    $(".front-loading").height($("body").height());
			
	guidely.add ({
		attachTo: '#div-menu'
		, anchor: 'top-left'
		, title: 'Guide 1/6'
		, text: 'This is the menu, use it to travel through the platform'
	});
	
	guidely.add ({
		attachTo: '#div-teams'
		, anchor: 'top-left'
		, title: 'Guide 2/6'
		, text: 'Here you will be able to create a team o join your friend teams providing its code'
	});
	
	guidely.add ({
		attachTo: '#div-personal'
		, anchor: 'top-left'
		, title: 'Guide 3/6'
		, text: 'Change your name, set up a description and pick up an avatar!'
	});
	
	guidely.add ({
		attachTo: '#div-bikes'
		, anchor: 'top-left'
		, title: 'Guide 4/6'
		, text: 'Add or Remove your bikes'
	});
	
	guidely.add ({
		attachTo: '#div-skills'
		, anchor: 'top-left'
		, title: 'Guide 5/6'
		, text: 'Score you skills from 1 to 10!'
	});
	
	guidely.add ({
		attachTo: '#div-joined'
		, anchor: 'top-left'
		, title: 'Guide 6/6'
		, text: 'Here you can see how many stages you have joined'
	});
	
	guidely.init ({ welcome: false, startTrigger: true, showOnStart:false});
});