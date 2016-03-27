package org.ernest.applications.bt.gui.services.impl;

import org.ernest.applications.bt.db.manager.credentials.ct.CreateCredentialsInput;
import org.ernest.applications.bt.db.manager.credentials.ct.ValidateInput;
import org.ernest.applications.bt.db.manager.credentials.ct.ValidateOutput;
import org.ernest.applications.bt.gui.services.AuthDataService;
import org.ernest.applications.bt.gui.services.MailService;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.ernest.applications.bt.manager.mails.ct.SendActivateInput;
import org.ernest.applications.bt.manager.mails.ct.SendRecoverInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthDataServiceImpl implements AuthDataService {

	@Value("${service.credentials.port}")
	String credentialsPort;
	
	@Autowired
	UserDataService userDataService;
	
	@Autowired
	MailService mailService;
	
	@Override
	public String validate(String name, String password) {
		ValidateInput input = new ValidateInput();
		input.setName(name);
		input.setPass(password);
		
		return new RestTemplate().postForObject("http://localhost:" + credentialsPort + "/validate", input, ValidateOutput.class).getUserId();
	}

	@Override
	public void createUser(String name, String email, String pass) {
		CreateCredentialsInput input = new CreateCredentialsInput();
		input.setName(name);
		input.setEmail(email);
		input.setPass(pass);
		
		String token = new RestTemplate().postForObject("http://localhost:" + credentialsPort + "/create", input, String.class);
		mailService.activate(token, name, email);
	}

	@Override
	public void activate(String token) {
		String userName = new RestTemplate().getForObject("http://localhost:" + credentialsPort + "/activate/"+token, String.class);
		userDataService.create(token);
		userDataService.saveName(token, userName);
	}

	@Override
	public void recover(String email) {
		String pass = new RestTemplate().postForObject("http://localhost:" + credentialsPort + "/recover", email, String.class);
		mailService.recover(email, pass);
	}
}