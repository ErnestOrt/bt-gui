package org.ernest.applications.bt.gui.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ernest.applications.bt.db.manager.teams.ct.UpdateAddCommentInput;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateAddMemberInput;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateAddStageCompletedInput;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateNameInput;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateRemoveMemberInput;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateRemoveStageCompleteInput;
import org.ernest.applications.bt.db.manager.teams.ct.entities.Team;
import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.CommentDataService;
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
	
	@Autowired
	CommentDataService commentDataService;
	
	@Override
	public TeamDto getTeam(String teamId) {
		Team team = new RestTemplate().getForObject("http://localhost:" + teamsPort + "/retrieve/"+teamId, Team.class);
		
		TeamDto teamDto = new TeamDto();
		teamDto.setId(team.get_id());
		teamDto.setName(team.getName());
		
		team.getStagesCompletedIds().forEach(stageId -> { teamDto.getStages().add(stagesDataService.getStage(stageId)); });
		team.getMembersIds().forEach(memberId -> { teamDto.getMembers().add(userDataService.getUser(memberId)); });
		Collections.reverse(team.getCommentIds());
		team.getCommentIds().stream().limit(50).forEach(commentId -> { teamDto.getComments().add(commentDataService.getComment(commentId, teamDto.getMembers())); });
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

	@Override
	public void addComment(String teamId, String commentId) {
		UpdateAddCommentInput updateAddCommentInput = new UpdateAddCommentInput();
		updateAddCommentInput.setCommentId(commentId);
		updateAddCommentInput.setTeamId(teamId);
		
		new RestTemplate().postForObject("http://localhost:" + teamsPort + "/update/addcomment", updateAddCommentInput, String.class);
	}

	@Override
	public String create(String name, String userId) {
		String teamId = new RestTemplate().getForObject("http://localhost:" + teamsPort + "/create", String.class);
		
		UpdateNameInput updateNameInput = new UpdateNameInput();
		updateNameInput.setTeamId(teamId);
		updateNameInput.setName(name);
		new RestTemplate().postForObject("http://localhost:" + teamsPort + "/update/name", updateNameInput, String.class);
		
		UpdateAddMemberInput updateAddMemberInput = new UpdateAddMemberInput();
		updateAddMemberInput.setTeamId(teamId);
		updateAddMemberInput.setMemberId(userId);
		new RestTemplate().postForObject("http://localhost:" + teamsPort + "/update/addmember", updateAddMemberInput, String.class);
		
		return teamId;
	}
	
	@Override
	public void unjoinTeam(String teamId, String userId) {
		UpdateRemoveMemberInput updateRemoveMemberInput = new UpdateRemoveMemberInput();
		updateRemoveMemberInput.setTeamId(teamId);
		updateRemoveMemberInput.setMemberId(userId);
		
		new RestTemplate().postForObject("http://localhost:" + teamsPort + "/update/removemember", updateRemoveMemberInput, String.class);
	}

	@Override
	public void joinTeam(String teamId, String userId) {
		UpdateAddMemberInput updateAddMemberInput = new UpdateAddMemberInput();
		updateAddMemberInput.setTeamId(teamId);
		updateAddMemberInput.setMemberId(userId);
		
		new RestTemplate().postForObject("http://localhost:" + teamsPort + "/update/addmember", updateAddMemberInput, String.class);
	}
}