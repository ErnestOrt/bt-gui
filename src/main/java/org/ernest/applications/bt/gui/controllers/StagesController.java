package org.ernest.applications.bt.gui.controllers;

import java.text.ParseException;
import java.util.List;

import org.ernest.applications.bt.gui.services.StagesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value= "/stages/create", method = RequestMethod.POST)
	@ResponseBody
    public void saveSkills(@RequestParam(value="name") String name, 
    					   @RequestParam(value="date") String date,
    					   @RequestParam(value="kilomitersTotal") int kilomitersTotal,
    					   @RequestParam(value="kilomiters") List<Double> kilomiters,
    					   @RequestParam(value="elevation") List<Double> elevation) throws ParseException {
		
		stagesDataService.create(teamIdStatic, name, date, kilomitersTotal, kilomiters, elevation);
	}
}