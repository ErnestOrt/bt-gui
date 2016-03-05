package org.ernest.applications.bt.gui.services;

import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.teams.ct.entities.Team;
import org.ernest.applications.bt.gui.dtos.StageDto;
import org.ernest.applications.bt.gui.dtos.TeamDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StagesDataServiceImpl implements StagesDataService {

	@Override
	public TeamDto getStages(String teamId) {
		Team team = new RestTemplate().getForObject("http://localhost:7777/retrieve/"+teamId, Team.class);
		
		TeamDto teamDto = new TeamDto();
		teamDto.setName(team.getName());
		
		team.getStagesCompletedIds().forEach(stageId -> {
			Stage stage = new RestTemplate().getForObject("http://localhost:9988/retrieve/"+stageId, Stage.class);
			
			StageDto stageDto = new StageDto();
			stageDto.setId(stage.get_id());
			stageDto.setName(stage.getName());
			stageDto.setKilomitersTotal(stage.getKilomitersTotal());
			stageDto.setAltitudeByKilomiter(stage.getAltitudeByKilomiter());
			stageDto.setJoinedMembers(stage.getJoinedMembers());
			stageDto.setDate(stage.getDate());

			teamDto.getStages().add(stageDto); 
		});

		return teamDto;
	}
}