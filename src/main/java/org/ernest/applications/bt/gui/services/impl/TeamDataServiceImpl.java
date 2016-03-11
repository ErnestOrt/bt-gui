package org.ernest.applications.bt.gui.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.ernest.applications.bt.db.manager.teams.ct.UpdateAddStageCompletedInput;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateRemoveStageCompleteInput;
import org.ernest.applications.bt.db.manager.teams.ct.entities.Team;
import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.StagesDataService;
import org.ernest.applications.bt.gui.services.TeamDataService;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamDataServiceImpl implements TeamDataService {

	@Value("${service.teams.port}")
	String teamsPort;
	
	@Autowired 
	UserDataService userDataService;
	
	@Autowired 
	StagesDataService stagesDataService;
	
	@Override
	public TeamDto getTeam(String teamId) {
		Team team = new RestTemplate().getForObject("http://localhost:" + teamsPort + "/retrieve/"+teamId, Team.class);
		
		TeamDto teamDto = new TeamDto();
		teamDto.setName(team.getName());
		
		team.getStagesCompletedIds().forEach(stageId -> { teamDto.getStages().add(stagesDataService.getStage(stageId)); });
		team.getMembersIds().forEach(memberId -> { teamDto.getMembers().add(userDataService.getUser(memberId)); });
		return teamDto;
	}
	
	@Override
	public List<UserDto> getTeamMembers(String teamId) {
		List<UserDto> members = new ArrayList<UserDto>();
		Team team = new RestTemplate().getForObject("http://localhost:" + teamsPort + "/retrieve/"+teamId, Team.class);
		team.getMembersIds().forEach(memberId -> { members.add(userDataService.getUser(memberId)); });

		return members;
	}
	
	@Override
	public List<StageDto> getTeamStages(String teamId) {
		List<StageDto> stagesDtoList = new ArrayList<StageDto>();
		
		Team team = new RestTemplate().getForObject("http://localhost:" + teamsPort + "/retrieve/"+teamId, Team.class);
		team.getStagesCompletedIds().forEach(stageId -> { stagesDtoList.add(stagesDataService.getStage(stageId)); });

		return stagesDtoList;
	}

	@Override
	public void deleteStageCompleted(String teamId, String stageId) {
		UpdateRemoveStageCompleteInput updateRemoveStageCompleteInput = new UpdateRemoveStageCompleteInput();
		updateRemoveStageCompleteInput.setTeamId(teamId);
		updateRemoveStageCompleteInput.setStageId(stageId);
		
		new RestTemplate().postForObject("http://localhost:" + teamsPort + "/update/removestagecompleted", updateRemoveStageCompleteInput, String.class);
	}

	@Override
	public void addStageCompleted(String teamId, String stageId) {
		UpdateAddStageCompletedInput updateAddStageCompletedInput = new UpdateAddStageCompletedInput();
		updateAddStageCompletedInput.setTeamId(teamId);
		updateAddStageCompletedInput.setStageId(stageId);
		
		new RestTemplate().postForObject("http://localhost:" + teamsPort + "/update/addstagecompleted", updateAddStageCompletedInput, String.class);
	}
}