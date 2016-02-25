package org.ernest.applications.bt.gui.controllers;

import org.ernest.applications.bt.gui.dtos.UserDto;
import org.ernest.applications.bt.gui.services.ProfileDataService;
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
	ProfileDataService profileDataService;

	@RequestMapping("/profile")
	public String getProfile(Model model) {
		
		UserDto userDto = profileDataService.getUser(userIdStatic);
		
		model.addAttribute("name", userDto.getName());
		model.addAttribute("description", userDto.getDescription());
		model.addAttribute("statistics", userDto.getStatistics());
		model.addAttribute("bikes", userDto.getBikesList());
		
		return "profile";
	}

	@RequestMapping(value= "/savepersonalinformation", method = RequestMethod.POST)
	@ResponseBody
    public void savePersonalInformation(@RequestParam(value="name") String name, @RequestParam(value="description") String description) {
		if(!name.isEmpty()) profileDataService.saveName(userIdStatic, name);
		if(!description.isEmpty()) profileDataService.saveDescription(userIdStatic, description);
	}
	
	@RequestMapping(value= "/saveskills", method = RequestMethod.POST)
	@ResponseBody
    public void saveSkills(@RequestParam(value="resistence") int resistence, 
    					   @RequestParam(value="sprint") int sprint,
    					   @RequestParam(value="montain") int montain,
    					   @RequestParam(value="flat") int flat,
    					   @RequestParam(value="btt") int btt,
    					   @RequestParam(value="road") int road) {
		
		profileDataService.saveSkills(userIdStatic, resistence, sprint, montain, flat, btt, road);
	}
}