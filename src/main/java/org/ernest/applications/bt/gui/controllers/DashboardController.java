package org.ernest.applications.bt.gui.controllers;

import java.util.Comparator;
import java.util.Date;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.services.TeamDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
	
	@Value("${team.id.static}")
	private String teamIdStatic;
	
	@Value("${user.id.static}")
	private String userIdStatic;

	@Autowired
	TeamDataService teamDataService;
	
	@RequestMapping("/dashboard")
	public String getDashboard(Model model) {
		TeamDto team = teamDataService.getTeam(teamIdStatic);
		
		model.addAttribute("totalMembers", team.getMembers().size());
		model.addAttribute("totalStages", team.getStages().size());
		model.addAttribute("totalKilomiters", team.getStages().stream().reduce(0, (sum, stage) -> sum += stage.getKilomitersTotal(), (sum1, sum2) -> sum1 + sum2));
		model.addAttribute("totalBikes", team.getMembers().stream().reduce(0, (sum, member) -> sum += member.getBikesList().size(), (sum1, sum2) -> sum1 + sum2));
		
		model.addAttribute("memberId", userIdStatic);
		model.addAttribute("members", team.getMembers());
		model.addAttribute("stages", team.getStages());
		model.addAttribute("stageCloser", team.getStages().stream()
														  .sorted(Comparator.comparing(StageDto::getDate))
														  .filter(stage -> stage.getDate().compareTo(new Date()) >= 0)
														  .findFirst()
														  .get());
		return "dashboard";
	}
}