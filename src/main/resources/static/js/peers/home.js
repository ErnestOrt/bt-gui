function guide(menu, stats, stage, calendar, news) {
	
	guidely.add ({attachTo: '#div-menu', anchor: 'top-left', title: 'Guide 1/5', text: menu});
	guidely.add ({attachTo: '#div-stats', anchor: 'top-left', title: 'Guide 2/5', text: stats});
	guidely.add ({attachTo: '#div-stage', anchor: 'top-left', title: 'Guide 3/5', text: stage});
	guidely.add ({attachTo: '#div-calendar', anchor: 'top-left', title: 'Guide 4/5', text: calendar});
	guidely.add ({attachTo: '#div-news', anchor: 'top-left', title: 'Guide 5/5', text: news});
	
	guidely.init ({welcome: false, startTrigger: true, showOnStart:false});	
}