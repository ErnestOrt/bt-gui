	$(".front-loading").hide();
	$(".front-loading").height($("body").height());
	
	$(function () {
				
				guidely.add ({
					attachTo: '#div-menu'
					, anchor: 'top-left'
					, title: 'Guide 1/6'
					, text: 'This is the menu, use it to travel through the platform.'
				});
				
				guidely.add ({
					attachTo: '#div-stats'
					, anchor: 'top-left'
					, title: 'Guide 2/6'
					, text: 'How many members the team has? How many stages or kilometers have the team completed? How many bikes do it has?'
				});
				
				
				guidely.add ({
					attachTo: '#div-calendar'
					, anchor: 'top-left'
					, title: 'Guide 3/6'
					, text: 'Here is the calendar where all stages of the team apear. Mark with red, those you will not take part. Mark with green, you are in! Click on them to visit them.'
				});
				
				guidely.add ({
					attachTo: '#div-members'
					, anchor: 'top-left'
					, title: 'Guide 4/6'
					, text: 'See all team members.'
				});
				
				guidely.add ({
					attachTo: '#div-comments'
					, anchor: 'top-left'
					, title: 'Guide 5/6'
					, text: 'Comment about whatever related or not to the team.'
				});
				
				guidely.add ({
					attachTo: '#div-stages'
					, anchor: 'top-left'
					, title: 'Guide 6/6'
					, text: 'See all team stages.'
				});
				
				guidely.init ({ welcome: false, startTrigger: true, showOnStart:false });
			});