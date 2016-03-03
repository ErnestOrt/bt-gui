package org.ernest.applications.bt.gui.services;

import org.ernest.applications.bt.gui.dtos.TeamDto;

public interface MembersDataService {

	TeamDto getMembers(String teamId);

}
