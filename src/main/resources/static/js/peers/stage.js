
  $(".front-loading").hide();
  $(".front-loading").height($("body").height());    
    

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
    

function guide(menu, stats, members, altitude, route) {
	
	guidely.add ({attachTo: '#div-menu', anchor: 'top-left', title: 'Guide 1/5', text: menu});
	guidely.add ({attachTo: '#div-stats', anchor: 'top-left', title: 'Guide 2/5', text: stats});
	guidely.add ({attachTo: '#div-members', anchor: 'top-left', title: 'Guide 3/5', text: members});
	guidely.add ({attachTo: '#div-altitude', anchor: 'top-left', title: 'Guide 4/5', text: altitude});
	guidely.add ({attachTo: '#div-route', anchor: 'top-left', title: 'Guide 5/5', text: route});
	
	guidely.init ({welcome: false, startTrigger: true, showOnStart:false});	
}