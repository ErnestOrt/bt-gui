package org.ernest.applications.bt.gui.services.impl;

import org.ernest.applications.bt.gui.services.MailService;
import org.ernest.applications.bt.manager.mails.ct.SendActivateInput;
import org.ernest.applications.bt.manager.mails.ct.SendRecoverInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailServiceImpl implements MailService {

	@Value("${service.mails.port}")
	String mailsPort;

	@Override
	public void activate(String token, String name, String email) {
		SendActivateInput sendActivateInput = new SendActivateInput();
		sendActivateInput.setToken(token);
		sendActivateInput.setUsermail(email);
		sendActivateInput.setUserName(name);
		
		new RestTemplate().postForObject("http://localhost:" + mailsPort + "/sendactivate", sendActivateInput, String.class);
	}

	@Override
	public void recover(String email, String pass) {
		SendRecoverInput sendRecoverInput = new SendRecoverInput();
		sendRecoverInput.setEmail(email);
		sendRecoverInput.setPass(pass);
		
		new RestTemplate().postForObject("http://localhost:" + mailsPort + "/sendrecover", sendRecoverInput, String.class);
	}
}
