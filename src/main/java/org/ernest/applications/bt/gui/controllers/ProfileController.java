package org.ernest.applications.bt.gui.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {
	
	@Value("${user.id.static}")
	private String userIdStatic;
	
	@Autowired
	UserDataService userDataService;

	@RequestMapping("/profile")
	public String getProfile(Model model) {
		
		UserDto userDto = userDataService.getUser(userIdStatic);
		
		model.addAttribute("name", userDto.getName());
		model.addAttribute("avatarIcon", userDto.getAvatarIcon());
		model.addAttribute("description", userDto.getDescription());
		model.addAttribute("statistics", userDto.getStatistics());
		model.addAttribute("bikes", userDto.getBikesList());
		model.addAttribute("stagesJoined", userDataService.getUserStagesJoinnedMap(userIdStatic));
		model.addAttribute("currentMonth", new Date().getMonth());
		
		return "profile";
	}

	@RequestMapping(value= "/profile/savepersonalinformation", method = RequestMethod.POST)
	@ResponseBody
    public void savePersonalInformation(@RequestParam(value="name") String name, @RequestParam(value="description") String description, @RequestParam(value="icon") String icon) {
		if(!name.isEmpty()) userDataService.saveName(userIdStatic, name);
		if(!description.isEmpty()) userDataService.saveDescription(userIdStatic, description);
		userDataService.saveAvatarIcon(userIdStatic, icon);
	}
	
	@RequestMapping(value= "/profile/saveskills", method = RequestMethod.POST)
	@ResponseBody
    public void saveSkills(@RequestParam(value="resistence") int resistence, 
    					   @RequestParam(value="sprint") int sprint,
    					   @RequestParam(value="montain") int montain,
    					   @RequestParam(value="flat") int flat,
    					   @RequestParam(value="btt") int btt,
    					   @RequestParam(value="road") int road) {
		
		userDataService.saveSkills(userIdStatic, resistence, sprint, montain, flat, btt, road);
	}
	
	@RequestMapping(value = "/profile/addbike", method = RequestMethod.POST)
	@ResponseBody
	public void addBike(@RequestParam(value="name") String name) throws IOException {
		
		userDataService.addBike(userIdStatic, name);
	}
	
	@RequestMapping(value= "/profile/deltebikes", method = RequestMethod.POST)
	@ResponseBody
    public void deleteBikes(@RequestParam(value="ids") String bikesIds) {
		userDataService.deleteBikes(userIdStatic, Arrays.asList(bikesIds.split(",")));
	}
	
	

}