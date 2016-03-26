
  $(".front-loading").hide();
  $(".front-loading").height($("body").height());    

  $('button.followButton').hover(function(){
         $button = $(this);
        if($button.hasClass('following')){
            $button.addClass('unfollow');
            $button.text('Giving up');
        }
    }, function(){
        if($button.hasClass('following')){
            $button.removeClass('unfollow');
            $button.text("I'm in");
        }
    });
    

	var map = new GMaps(
	  {
	   el: '#map',
	   lat:47.366009,
	   lng:9.909770,
	   zoom:1
	});

function fillRute(stagePointsList){
	for(indexStagePoint = 0; indexStagePoint < stagePointsList.length; indexStagePoint++){
	   	 map.addMarker({lat:stagePointsList[indexStagePoint].latitude, lng:stagePointsList[indexStagePoint].longitude, title: 'Km '+stagePointsList[indexStagePoint].kilomiter});
	}
} 
    
$(function () {
		
		guidely.add ({
			attachTo: '#div-menu'
			, anchor: 'top-left'
			, title: 'Guide 1/5'
			, text: 'This is the menu, use it to travel through the platform'
		});
		
		guidely.add ({
			attachTo: '#div-stats'
			, anchor: 'top-left'
			, title: 'Guide 2/5'
			, text: 'Stage statistics and delete button.'
		});
		
		guidely.add ({
			attachTo: '#div-members'
			, anchor: 'top-left'
			, title: 'Guide 3/5'
			, text: 'See who will come and also join and unjoin yourself.'
		});
		
		guidely.add ({
			attachTo: '#div-altitude'
			, anchor: 'top-left'
			, title: 'Guide 4/5'
			, text: 'Here is the altitude map of the stages.'
		});
		
		guidely.add ({
			attachTo: '#div-route'
			, anchor: 'top-left'
			, title: 'Guide 5/5'
			, text: 'See the route you will follow.'
		});
		
		guidely.init ({ welcome: false, startTrigger: true, showOnStart:false });
	});