package org.ernest.applications.bt.gui.controllers;

import java.util.Comparator;
import java.util.Date;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.StagePointDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.services.CommentDataService;
import org.ernest.applications.bt.gui.services.TeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {
	
	@Value("${team.id.static}")
	private String teamIdStatic;
	
	@Value("${user.id.static}")
	private String userIdStatic;

	@Autowired
	TeamDataService teamDataService;
	
	@Autowired
	CommentDataService commentDataService;
	
	@RequestMapping("/dashboard")
	public String getDashboard(Model model) {
		TeamDto team = teamDataService.getTeam(teamIdStatic);
		
		model.addAttribute("totalMembers", team.getMembers().size());
		model.addAttribute("totalStages", team.getStages().size());
		model.addAttribute("totalKilomiters", team.getStages().stream().reduce(0, (sum, stage) -> sum += stage.getKilomitersTotal(), (sum1, sum2) -> sum1 + sum2));
		model.addAttribute("totalBikes", team.getMembers().stream().reduce(0, (sum, member) -> sum += member.getBikesList().size(), (sum1, sum2) -> sum1 + sum2));
		
		model.addAttribute("memberId", userIdStatic);
		model.addAttribute("members", team.getMembers());
		model.addAttribute("comments", team.getComments());
		model.addAttribute("stages", team.getStages());
		model.addAttribute("stageCloser", team.getStages().stream()
														  .sorted(Comparator.comparing(StageDto::getDate))
														  .filter(stage -> stage.getDate().compareTo(new Date()) >= 0)
														  .findFirst()
														  .orElse(buildEmptyStage()));
		return "dashboard";
	}
	
	@RequestMapping(value= "/dashboard/comment/create", method = RequestMethod.POST)
	@ResponseBody
    public void createComment(@RequestParam(value="content") String content) {
		String commentId = commentDataService.create(userIdStatic, content);
		teamDataService.addComment(teamIdStatic, commentId);
	}

	private StageDto buildEmptyStage() {
		StageDto stageDto = new StageDto();
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