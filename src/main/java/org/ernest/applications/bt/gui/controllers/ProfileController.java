package org.ernest.applications.bt.gui.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value= "/profile/savepersonalinformation", method = RequestMethod.POST)
	@ResponseBody
    public void savePersonalInformation(@RequestParam(value="name") String name, @RequestParam(value="description") String description) {
		if(!name.isEmpty()) profileDataService.saveName(userIdStatic, name);
		if(!description.isEmpty()) profileDataService.saveDescription(userIdStatic, description);
	}
	
	@RequestMapping(value= "/profile/saveskills", method = RequestMethod.POST)
	@ResponseBody
    public void saveSkills(@RequestParam(value="resistence") int resistence, 
    					   @RequestParam(value="sprint") int sprint,
    					   @RequestParam(value="montain") int montain,
    					   @RequestParam(value="flat") int flat,
    					   @RequestParam(value="btt") int btt,
    					   @RequestParam(value="road") int road) {
		
		profileDataService.saveSkills(userIdStatic, resistence, sprint, montain, flat, btt, road);
	}
	
	@RequestMapping(value = "/profile/addbike", method = RequestMethod.POST)
	@ResponseBody
	public void addBike(@RequestParam(value="name") String name) throws IOException {
		
		profileDataService.addBike(userIdStatic, name);
	}
	
	@RequestMapping(value= "/profile/deltebikes", method = RequestMethod.POST)
	@ResponseBody
    public void deleteBikes(@RequestParam(value="ids") String bikesIds) {
		profileDataService.deleteBikes(userIdStatic, Arrays.asList(bikesIds.split(",")));
	}
	
	

}