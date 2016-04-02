package org.ernest.applications.bt.gui.controllers;

import org.ernest.applications.bt.gui.services.AuthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
	
	@Autowired
	AuthDataService authDataService;

	@RequestMapping("/login")
	public String logIn(Model model) {
		return "login";
	}
	
	@RequestMapping("/signup")
	public String signUp(Model model) {
		return "signup";
	}
	
	@RequestMapping("/404")
	public String error(Model model) {
		return "404";
	}
	
	@RequestMapping("/recovery")
	public String recovery(Model model) {
		return "recovery";
	}
	
	@RequestMapping(value= "/recover", method = RequestMethod.POST)
	@ResponseBody
    public void recover(@RequestParam(value="email") String email) {
		authDataService.recover(email);	
	}
	
	@RequestMapping(value= "/registration", method = RequestMethod.POST)
	@ResponseBody
    public void registration(@RequestParam(value="name") String name, 
    									@RequestParam(value="email") String email, 
    									@RequestParam(value="pass") String pass,
    									@RequestParam(value="passRepeated") String passRepeated) {

		if(pass.equals(passRepeated)){
			authDataService.createUser(name, email, pass);
		}
	}
	
	@RequestMapping(value="/activate/{token}",method = RequestMethod.GET)
	public String activateAccount(@PathVariable("token") String token){
		
		authDataService.activate(token);
		return "activated";
	}
}
