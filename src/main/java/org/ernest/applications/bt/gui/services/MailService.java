package org.ernest.applications.bt.gui.services;

import org.ernest.applications.bt.gui.entities.TeamDto;

public interface MailService {

	 void activate(String token, String name, String email);

	 void recover(String email, String pass);

	void sendNewStage(TeamDto team, String stageId, String date, String stageName, String userId);
}
