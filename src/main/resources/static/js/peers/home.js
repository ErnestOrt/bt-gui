$(function () {
	
	guidely.add ({
		attachTo: '#div-menu'
		, anchor: 'top-left'
		, title: 'Guide 1/5'
		, text: 'This is the menu, use it to travel through the platform.'
	});
	
	guidely.add ({
		attachTo: '#div-stats'
		, anchor: 'top-left'
		, title: 'Guide 2/5'
		, text: 'How many teams have I joined? How many stages or kilometers have I completed? How many bikes do I have?'
	});
	
	guidely.add ({
		attachTo: '#div-stage'
		, anchor: 'top-left'
		, title: 'Guide 3/5'
		, text: 'Here is your next stage, the current weather for it and the members joined. If there is no stage, create one!'
	});
	
	guidely.add ({
		attachTo: '#div-calendar'
		, anchor: 'top-left'
		, title: 'Guide 4/5'
		, text: 'Here is the calendar where all stages apear. Mark with red, those you will not take part. Mark with green, you are in! Click on them to visit them.'
	});
	
	guidely.add ({
		attachTo: '#div-news'
		, anchor: 'top-left'
		, title: 'Guide 5/5'
		, text: 'Check those fresh news.'
	});
	
	guidely.init ({welcome: false, startTrigger: true, showOnStart:false});
	
});