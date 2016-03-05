package org.ernest.applications.bt.gui.controllers;

import org.ernest.applications.bt.gui.services.StagesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StagesController {

	@Value("${team.id.static}")
	private String teamIdStatic;

	@Autowired
	StagesDataService stagesDataService;

	@RequestMapping("/stages")
	public String getStages(Model model) {
		model.addAttribute("stages", stagesDataService.getStages(teamIdStatic).getStages());
		return "stages";
	}

}