	$(".front-loading").hide();
	$(".front-loading").height($("body").height());
	
	function guide(menu, stats, calendar, members, comments, stages, notices) {
    	
    	guidely.add ({attachTo: '#div-menu', anchor: 'top-left', title: 'Guide 1/7', text: menu});
    	guidely.add ({attachTo: '#div-stats', anchor: 'top-left', title: 'Guide 2/7', text: stats});
    	guidely.add ({attachTo: '#div-calendar', anchor: 'top-left', title: 'Guide 3/7', text: calendar});
    	guidely.add ({attachTo: '#div-notices', anchor: 'top-left', title: 'Guide 4/7', text: notices});
    	guidely.add ({attachTo: '#div-members', anchor: 'top-left', title: 'Guide 5/7', text: members});
    	guidely.add ({attachTo: '#div-comments', anchor: 'top-left', title: 'Guide 6/7', text: comments});
    	guidely.add ({attachTo: '#div-stages', anchor: 'top-left', title: 'Guide 7/7', text: stages});
    	
    	guidely.init ({welcome: false, startTrigger: true, showOnStart:false});	
    }