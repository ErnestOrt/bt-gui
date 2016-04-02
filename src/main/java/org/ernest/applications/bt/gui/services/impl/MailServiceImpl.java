package org.ernest.applications.bt.gui.services.impl;

import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.services.MailService;
import org.ernest.applications.bt.manager.mails.ct.SendActivateInput;
import org.ernest.applications.bt.manager.mails.ct.SendAddMembersInput;
import org.ernest.applications.bt.manager.mails.ct.SendNewStageInput;
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

	@Override
	public void sendNewStage(TeamDto team, String stageId, String date, String stageName, String userId) {
		
		team.getMembers().forEach(user -> {
			if(!userId.equals(user.getId())){
				SendNewStageInput input = new SendNewStageInput();
				input.setStageId(stageId);
				input.setStageDate(date);
				input.setStageName(stageName);
				input.setTeamName(team.getName());
				input.setUserMail(user.getEmail());
				input.setUserName(user.getName());
				new RestTemplate().postForObject("http://localhost:" + mailsPort + "/sendnewstage", input, String.class);
			}
		});
	}

	@Override
	public void addMember(String teamId, String teamName, String userMailInvited, String userNameInvited, String userNameSender) {
		SendAddMembersInput input = new SendAddMembersInput();
		input.setTeamId(teamId);
		input.setTeamName(teamName);
		input.setUserMailInvited(userMailInvited);
		input.setUserNameInvited(userNameInvited);
		input.setUserNameSender(userNameSender);
		
		new RestTemplate().postForObject("http://localhost:" + mailsPort + "/sendaddmembers", input, String.class);
	}
}
