	$(".front-loading").hide();
	$(".front-loading").height($("body").height());
	
	function guide(menu, stats, calendar, members, comments, stages) {
    	
    	guidely.add ({attachTo: '#div-menu', anchor: 'top-left', title: 'Guide 1/6', text: menu});
    	guidely.add ({attachTo: '#div-stats', anchor: 'top-left', title: 'Guide 2/6', text: stats});
    	guidely.add ({attachTo: '#div-calendar', anchor: 'top-left', title: 'Guide 3/6', text: calendar});
    	guidely.add ({attachTo: '#div-members', anchor: 'top-left', title: 'Guide 4/6', text: members});
    	guidely.add ({attachTo: '#div-comments', anchor: 'top-left', title: 'Guide 5/6', text: comments});
    	guidely.add ({attachTo: '#div-stages', anchor: 'top-left', title: 'Guide 6/6', text: stages});
    	
    	guidely.init ({welcome: false, startTrigger: true, showOnStart:false});	
    }