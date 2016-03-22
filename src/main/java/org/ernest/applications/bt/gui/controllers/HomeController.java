package org.ernest.applications.bt.gui.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.StagePointDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.CommentDataService;
import org.ernest.applications.bt.gui.services.NewsDataService;
import org.ernest.applications.bt.gui.services.TeamDataService;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Autowired
	TeamDataService teamDataService;
	
	@Autowired
	NewsDataService newsDataService;
	
	@Autowired
	UserDataService userDataService;
	
	@RequestMapping("/home")
	public String getDashboard(Model model) {
		UserDto userDto = userDataService.getUser((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		List<TeamDto> teams = userDto.getTeamsJoined().stream().map(teamId -> {return teamDataService.getTeam(teamId);}).collect(Collectors.toList());
		
		List<StageDto> stages = teams.stream()
									  .map(team -> team.getStages())
									  .flatMap(l -> l.stream())
									  .sorted(Comparator.comparing(StageDto::getDate))
									  .collect(Collectors.toList());
		
		model.addAttribute("totalTeams", userDto.getTeamsJoined().size());
		model.addAttribute("totalStages", userDto.getStagesIdsJoined().size());
		model.addAttribute("totalKilomiters", stages.stream().reduce(0, (sum, stage) -> sum += stage.getKilomitersTotal(), (sum1, sum2) -> sum1 + sum2));
		model.addAttribute("totalBikes", userDto.getBikesList().size()); //team.getMembers().stream().reduce(0, (sum, member) -> sum += member.getBikesList().size(), (sum1, sum2) -> sum1 + sum2));
		
		model.addAttribute("articles", newsDataService.get());
		
		model.addAttribute("memberId", (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		model.addAttribute("stages", stages);
		StageDto stageCloser = teams.stream()
									  .map(team -> team.getStages())
									  .flatMap(l -> l.stream())
									  .sorted(Comparator.comparing(StageDto::getDate))
									  .filter(stage -> stage.getDate().compareTo(new Date()) >= 0)
									  .findFirst()
									  .orElse(buildEmptyStage());
		model.addAttribute("stageCloser", stageCloser); 
		
		if(stageCloser.getId() != null){
			model.addAttribute("members", teams.stream()
											 .filter(team -> team.getStages().stream().anyMatch(stage -> stage.getId().equals(stageCloser.getId())))
											 .findFirst().get()
											 .getMembers());
		}else{
			model.addAttribute("members", new ArrayList<UserDto>());
		}
		  
		return "home";
	}

	private StageDto buildEmptyStage() {
		StageDto stageDto = new StageDto();
		stageDto.setName("Join a team or create a stage...");
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		return stageDto;
	}
}