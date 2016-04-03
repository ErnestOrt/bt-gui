package org.ernest.applications.bt.gui.controllers;

import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.StagePointDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.entities.ValidationInfo;
import org.ernest.applications.bt.gui.services.CommentDataService;
import org.ernest.applications.bt.gui.services.MailService;
import org.ernest.applications.bt.gui.services.NoticeDataService;
import org.ernest.applications.bt.gui.services.TeamDataService;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeamsController {

	@Autowired
	CommentDataService commentDataService;
	
	@Autowired
	NoticeDataService noticeDataService;
	
	@Autowired
	UserDataService userDataService;
	
	@Autowired
	TeamDataService teamDataService;
	
	@Autowired
	MailService mailService;
	
	@RequestMapping("/teams")
	public String getTeams(Model model) {
		UserDto userDto = userDataService.getUser(((ValidationInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
		model.addAttribute("teams", userDto.getTeamsJoined().stream().map(teamId -> {return teamDataService.getTeam(teamId);}).collect(Collectors.toList()));
		model.addAttribute("memberName", ((ValidationInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserName());

		return "teams";
	}
	
	@RequestMapping(value= "/team/comment/create", method = RequestMethod.POST)
	@ResponseBody
    public void createComment(@RequestParam(value="content") String content, @RequestParam(value="teamId") String teamId) {
		String commentId = commentDataService.create(((ValidationInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId(), content);
		teamDataService.addComment(teamId, commentId);
	}
	
	@RequestMapping(value= "/team/notice/create", method = RequestMethod.POST)
	@ResponseBody
    public void createNotice(@RequestParam(value="content") String content, @RequestParam(value="teamId") String teamId, @RequestParam(value="title") String title) {
		TeamDto teamDto = teamDataService.getTeam(teamId);
		teamDataService.addNotice(teamId, noticeDataService.create(content, title));
		mailService.addNotice(teamDto, title, content, ((ValidationInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
	}
	
	@RequestMapping(path = "/team/{teamId}", method = RequestMethod.GET)
	public String getTeam(@PathVariable("teamId") String teamId, Model model) {
		TeamDto teamDto = teamDataService.getTeam(teamId);

		
		model.addAttribute("totalMembers", teamDto.getMembers().size());
		model.addAttribute("totalStages", teamDto.getStages().size());
		model.addAttribute("totalKilomiters", teamDto.getStages().stream().reduce(0, (sum, stage) -> sum += stage.getKilomitersTotal(), (sum1, sum2) -> sum1 + sum2));
		model.addAttribute("totalBikes", teamDto.getMembers().stream().reduce(0, (sum, member) -> sum += member.getBikesList().size(), (sum1, sum2) -> sum1 + sum2));
		model.addAttribute("memberName", ((ValidationInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserName());
		model.addAttribute("memberId", ((ValidationInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
		model.addAttribute("stages", teamDto.getStages().stream().sorted(Comparator.comparing(StageDto::getDate)).collect(Collectors.toList()));
		StageDto stageCloser = teamDto.getStages().stream()
									  .sorted(Comparator.comparing(StageDto::getDate))
									  .filter(stage -> stage.getDate().compareTo(new Date()) >= 0)
									  .findFirst()
									  .orElse(ControllersUtils.buildEmptyStage());
		model.addAttribute("stageCloser", stageCloser); 
		model.addAttribute("members", teamDto.getMembers());
		model.addAttribute("comments", teamDto.getComments());
		model.addAttribute("notices", teamDto.getNotices());
		
		  
		return "team";
	}
	
	@RequestMapping(value= "/team/addmember", method = RequestMethod.POST)
	@ResponseBody
    public void addMember(@RequestParam(value="email") String email, @RequestParam("teamId") String teamId) {
		TeamDto teamDto = teamDataService.getTeam(teamId);
		teamDataService.addMember(teamId, email);
		mailService.addMember(teamId, teamDto.getName(), email, email, ((ValidationInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserName());
	}
}
