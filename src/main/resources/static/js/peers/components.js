function validateField(fieldId, previosResult){
        	var result = true;
        	if($('#' + fieldId).val()==""){
        		$('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("invalid");
         		result = false;
         	}else{
         		$('#' + fieldId).removeClass("invalid").removeClass("valid").addClass("valid");
         	}
        	if(previosResult == false){
        		result = false;
        	}
        	return result;
        }

function createStageGraph(stagePointsList, chartName){
	var lineChartData
	var valuesStage =[];
	var valuesBackground =[];
	var labelsKilomiters = [];
    	
   	valuesBackground.push(0);
   	valuesBackground.push(1000);
   	
   	for(indexStagePoint = 0; indexStagePoint < stagePointsList.length; indexStagePoint++){
   		labelsKilomiters.push(stagePointsList[indexStagePoint].kilomiter);
   		valuesStage.push(stagePointsList[indexStagePoint].altitude);
	    if(indexStagePoint>=2){
			valuesBackground.push(0);
	  	}

   	}
   	
   	lineChartData = {
               labels:  labelsKilomiters,
               datasets: [
				{
				    fillColor: "rgba(255,255,255,0)",
    				    strokeColor: "rgba(255,255,255,0)",
    				    pointColor: "rgba(255,255,255,0)",
    				    pointStrokeColor: "#FFFFFF",
    				    pointHighlightFill: "#FFFFFF",
    				    pointHighlightStroke: "rgba(255,255,255,0)",
				    data: valuesBackground
				},
   				{
   				    fillColor: "rgba(151,187,205,0.5)",
   				    strokeColor: "rgba(151,187,205,1)",
   				    pointColor: "rgba(151,187,205,1)",
   				    pointStrokeColor: "#fff",
   				    data: valuesStage
   				}
   			]

           }
   	
   	new Chart(document.getElementById(chartName).getContext("2d")).Line(lineChartData);
}

function fillCalendar(stagesList, userId){
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();

	var eventsArray = [];
	var colorEvent;
	
	for (i = 0; i < stagesList.length; i++) {
		if(stagesList[i].joinedMembers.indexOf(userId) != -1){
			colorEvent='#67FA67';
		}else{
			colorEvent='#F78181';
		}
		
		eventsArray.push({title : stagesList[i].name, start : new Date(stagesList[i].date), url: "./stage/"+stagesList[i].id, color: colorEvent});
	}
	
	var calendar = $('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		selectable : false,
		selectHelper : true,
		editable : false,
		events : eventsArray
	});
}


function stagesJoined(currentMonth, stagesJoined){
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    var dataArray = [];
    
    if(stagesJoined[(currentMonth + 8)%12] != null){
   	 dataArray.push(stagesJoined[(currentMonth + 8)%12]);
    }else{
   	 dataArray.push(0);
    }
    
    if(stagesJoined[(currentMonth + 9)%12] != null){
   	 dataArray.push(stagesJoined[(currentMonth + 9)%12]);
    }else{
   	 dataArray.push(0);
    }

    if(stagesJoined[(currentMonth + 10)%12] != null){
   	 dataArray.push(stagesJoined[(currentMonth + 10)%12]);
    }else{
   	 dataArray.push(0);
    }
    
    if(stagesJoined[(currentMonth + 11)%12] != null){
   	 dataArray.push(stagesJoined[(currentMonth + 11)%12]);
    }else{
   	 dataArray.push(0);
    }
   	 
    if(stagesJoined[(currentMonth)%12] != null){
   	 dataArray.push(stagesJoined[(currentMonth)%12]);
    }else{
        dataArray.push(0);
    }
    
    if(stagesJoined[(currentMonth + 1)%12] != null){
   	 dataArray.push(stagesJoined[(currentMonth + 1)%12]);
    }else{
   	 dataArray.push(0);
    }
    
    if(stagesJoined[(currentMonth + 2)%12] != null){
   	 dataArray.push(stagesJoined[(currentMonth + 2)%12]);
    }else{
   	 dataArray.push(0);
    }

    
   	 var barChartData = {
           labels: [months[(currentMonth + 8)%12],
                    months[(currentMonth + 9)%12],
                    months[(currentMonth + 10)%12],
                    months[(currentMonth + 11)%12],
                    months[(currentMonth + 0)%12],
                    months[(currentMonth + 1)%12],
                    months[(currentMonth + 2)%12]],
           datasets: [
       {
           fillColor: "rgba(151,187,205,0.5)",
           strokeColor: "rgba(151,187,205,1)",
           highlightFill: "rgba(151,187,205,0.75)",
           highlightStroke: "rgba(151,187,205,1)",
           data: dataArray
       },
     ]
       }
	var myLine = new Chart(document.getElementById("bar-chart").getContext("2d")).Bar(barChartData);
}

function createSkills(resistence, sprint, montain, flat, btt, road){
	var data = {
    	    labels: ["Resistence", "Sprint", "Montain", "Flat", "BTT", "Road"],
    	    datasets: [
				{
				    label: "My First dataset",
				    fillColor: "rgba(255,255,255,0)",
				    strokeColor: "rgba(255,255,255,0)",
				    pointColor: "rgba(255,255,255,0)",
				    pointStrokeColor: "#FFFFFF",
				    pointHighlightFill: "#FFFFFF",
				    pointHighlightStroke: "rgba(255,255,255,0)",
				    data: [0, 2, 4, 6, 8, 10]
				},
    	        {
    	            label: "My Second dataset",
    	            fillColor: "rgba(151,187,205,0.2)",
    	            strokeColor: "rgba(151,187,205,1)",
    	            pointColor: "rgba(151,187,205,1)",
    	            pointStrokeColor: "#fff",
    	            pointHighlightFill: "#fff",
    	            pointHighlightStroke: "rgba(151,187,205,1)",
    	            data: [resistence, sprint, montain, flat, btt, road]
    	        }
    	    ]
    	};
    
    new Chart(document.getElementById("line-chart").getContext("2d")).Radar(data);
}