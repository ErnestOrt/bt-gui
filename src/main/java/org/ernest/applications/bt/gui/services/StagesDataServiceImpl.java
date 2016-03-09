package org.ernest.applications.bt.gui.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ernest.applications.bt.db.manager.stages.ct.UpdateMandatoryInformation;
import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateAddStageCompletedInput;
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

	@Override
	public void create(String teamId, String name, String dateString, int kilomitersTotal, List<Double> kilomiters, List<Double> elevation) throws ParseException {
		String satgeIdCreated = new RestTemplate().getForObject("http://localhost:9988/create", String.class);
		
		UpdateMandatoryInformation updateMandatoryInformation = new UpdateMandatoryInformation();
		updateMandatoryInformation.setStageId(satgeIdCreated);
		updateMandatoryInformation.setStageName(name);
		updateMandatoryInformation.setStageDate(new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(dateString));
		updateMandatoryInformation.setStageKilomitersTotal(kilomitersTotal);
		
		Map<Double, Double> altitude = new HashMap<Double, Double>();
		for(int index=0; index<kilomiters.size(); index++){
			altitude.put(kilomiters.get(index),elevation.get(index));
		}
		updateMandatoryInformation.setStageAltitudeByKilomiter(altitude);
	
		new RestTemplate().postForObject("http://localhost:9988/update/mandatoryinformation", updateMandatoryInformation, String.class);
		
		UpdateAddStageCompletedInput updateAddStageCompletedInput = new UpdateAddStageCompletedInput();
		updateAddStageCompletedInput.setTeamId(teamId);
		updateAddStageCompletedInput.setStageId(satgeIdCreated);
		
		new RestTemplate().postForObject("http://localhost:7777/update/addstagecompleted", updateAddStageCompletedInput, String.class);
		
	}
}