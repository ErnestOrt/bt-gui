package org.ernest.applications.bt.gui.services;

import java.util.List;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.entities.UserDto;

public interface TeamDataService {

	List<UserDto> getTeamMembers(String teamId);

	TeamDto getTeam(String teamId);

	List<StageDto> getTeamStages(String teamId);

	void deleteStageCompleted(String teamId, String stageId);

	void addStageCompleted(String teamId, String stageId);

}
