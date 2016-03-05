package org.ernest.applications.bt.gui.controllers;

import org.ernest.applications.bt.gui.services.MembersDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MembersController {

	@Value("${team.id.static}")
	private String teamIdStatic;
	
	@Autowired
	MembersDataService membersDataService;

	@RequestMapping("/members")
	public String getMembers(Model model) {
		
		model.addAttribute("members", membersDataService.getMembers(teamIdStatic).getMembers());
		return "members";
	}
}