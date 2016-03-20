package org.ernest.applications.bt.gui.controllers;

import java.util.Date;

import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.TeamDataService;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MembersController {
	
	@Autowired
	TeamDataService membersDataService;
	
	@Autowired
	UserDataService userDataService;

	@RequestMapping("/member/{memberId}")
	public String getMember(@PathVariable("memberId") String memberId, Model model) {
		
		UserDto userDto = userDataService.getUser(memberId);
		
		model.addAttribute("name", userDto.getName());
		model.addAttribute("description", userDto.getDescription());
		model.addAttribute("avatarIcon", userDto.getAvatarIcon());
		model.addAttribute("statistics", userDto.getStatistics());
		model.addAttribute("bikes", userDto.getBikesList());
		model.addAttribute("stagesJoined", userDataService.getUserStagesJoinnedMap(memberId));
		model.addAttribute("currentMonth", new Date().getMonth());
		
		return "member";
	}
}