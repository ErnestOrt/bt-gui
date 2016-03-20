package org.ernest.applications.bt.gui.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.StagesDataService;
import org.ernest.applications.bt.gui.services.TeamDataService;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StagesController {

	@Autowired
	StagesDataService stagesDataService;
	
	@Autowired
	TeamDataService teamDataService;
	
	@Autowired
	UserDataService userDataService;


	@RequestMapping("/stages")
	public String getStages(Model model) {
		UserDto userDto = userDataService.getUser((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		List<TeamDto> teams = userDto.getTeamsJoined().stream().map(teamId -> {return teamDataService.getTeam(teamId);}).collect(Collectors.toList());
		model.addAttribute("teams", teams);
		model.addAttribute("stages", teams.stream()
										  .map(team -> team.getStages())
										  .flatMap(l -> l.stream())
										  .sorted(Comparator.comparing(StageDto::getDate))
										  .collect(Collectors.toList()));
		return "stages";
	}

	@RequestMapping(value= "/stages/create", method = RequestMethod.POST)
	@ResponseBody
    public void saveSkills(@RequestParam(value="name") String name, 
    					   @RequestParam(value="teamId") String teamId, 
    					   @RequestParam(value="date") String date,
    					   @RequestParam(value="kilomitersTotal") int kilomitersTotal,
    					   @RequestParam(value="kilomiters") List<Double> kilomiters,
    					   @RequestParam(value="elevation") List<Double> elevation,
    					   @RequestParam(value="longitude") List<Double> longitude,
    					   @RequestParam(value="latitude") List<Double> latitude) throws ParseException {
		
		String stageId = stagesDataService.create(name, date, kilomitersTotal, kilomiters, elevation, longitude, latitude);
		teamDataService.addStageCompleted(teamId, stageId);
	}
	
	@RequestMapping("/stage/{stageId}")
	public String getStage(@PathVariable("stageId") String stageId, Model model) {
		StageDto stageDto = stagesDataService.getStage(stageId);
		
		model.addAttribute("stageId", stageId);
		model.addAttribute("userJoinedStage", stageDto.getJoinedMembers().contains((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		model.addAttribute("stageNameAndDate", stageDto.getName() + " ["+ stageDto.getDate() +"]");
		model.addAttribute("membersJoined", stageDto.getJoinedMembers().size());
		model.addAttribute("stageKilomitersTotal", stageDto.getKilomitersTotal());
		model.addAttribute("stagePoints", stageDto.getStagePoints());
		
		UserDto userDto = userDataService.getUser((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		List<TeamDto> teams = userDto.getTeamsJoined().stream().map(teamId -> {return teamDataService.getTeam(teamId);}).collect(Collectors.toList());
		
		model.addAttribute("members", teams.stream()
											 .filter(team -> team.getStages().stream().anyMatch(stage -> stage.getId().equals(stageId)))
											 .findFirst().get()
											 .getMembers());
		
		return "stage";
	}
	

	@RequestMapping(value= "/stages/join", method = RequestMethod.POST)
	@ResponseBody
    public void joinStage(@RequestParam(value="stageId") String stageId) throws ParseException {
		stagesDataService.joinStage((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal(), stageId);
		userDataService.joinStage((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal(), stageId);
	}
	
	@RequestMapping(value= "/stages/unjoin", method = RequestMethod.POST)
	@ResponseBody
    public void unjoinStage(@RequestParam(value="stageId") String stageId) throws ParseException {
		stagesDataService.unjoinStage((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal(), stageId);
		userDataService.unjoinStage((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal(), stageId);
	}
	
	@RequestMapping(value= "/stages/delete", method = RequestMethod.POST)
	@ResponseBody
    public void deleteStage(@RequestParam(value="stageId") String stageId) throws ParseException {
		UserDto userDto = userDataService.getUser((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		List<TeamDto> teams = userDto.getTeamsJoined().stream().map(teamId -> {return teamDataService.getTeam(teamId);}).collect(Collectors.toList());
		String teamId = teams.stream().filter(team-> team.getStages().stream().anyMatch(stage -> stage.getId().equals(stageId))).findAny().get().getId();
		userDataService.unjoinStage((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal(), stageId);
		teamDataService.deleteStageCompleted(teamId, stageId);
		stagesDataService.delete(stageId);
	}
}