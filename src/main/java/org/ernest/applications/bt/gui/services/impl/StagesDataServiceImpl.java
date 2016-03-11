package org.ernest.applications.bt.gui.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.ernest.applications.bt.db.manager.stages.ct.UpdateJoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateMandatoryInformation;
import org.ernest.applications.bt.db.manager.stages.ct.UpdateUnjoinStageInput;
import org.ernest.applications.bt.db.manager.stages.ct.entities.Stage;
import org.ernest.applications.bt.db.manager.stages.ct.entities.StagePoint;
import org.ernest.applications.bt.db.manager.teams.ct.UpdateAddStageCompletedInput;
import org.ernest.applications.bt.db.manager.teams.ct.entities.Team;
import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.StagePointDto;
import org.ernest.applications.bt.gui.entities.TeamDto;
import org.ernest.applications.bt.gui.services.StagesDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StagesDataServiceImpl implements StagesDataService {

	@Value("${service.stages.port}")
	String stagesPort;
	
	@Override
	public StageDto getStage(String stageId) {
		Stage stage = new RestTemplate().getForObject("http://localhost:" + stagesPort + " /retrieve/"+stageId, Stage.class);
		
		StageDto stageDto = new StageDto();
		stageDto.setId(stage.get_id());
		stageDto.setName(stage.getName());
		stageDto.setKilomitersTotal(stage.getKilomitersTotal());
		stageDto.setStagePoints(stage.getStagePoints().stream()
													  .map(stagePoint -> new StagePointDto(stagePoint.getKilomiter(), 
															  							   stagePoint.getAltitude(), 
															  							   stagePoint.getLongitude(), 
															  							   stagePoint.getLatitude()))
													  .collect(Collectors.toList()));
		stageDto.setJoinedMembers(stage.getJoinedMembers());
		stageDto.setDate(stage.getDate());
		return stageDto;	
	}

	@Override
	public String create(String name, String dateString, int kilomitersTotal, List<Double> kilomiters, List<Double> elevation, List<Double> longitude, List<Double> latitude) throws ParseException {
		String satgeIdCreated = new RestTemplate().getForObject("http://localhost:" + stagesPort + " /create", String.class);
		
		UpdateMandatoryInformation updateMandatoryInformation = new UpdateMandatoryInformation();
		updateMandatoryInformation.setStageId(satgeIdCreated);
		updateMandatoryInformation.setStageName(name);
		updateMandatoryInformation.setStageDate(new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(dateString));
		updateMandatoryInformation.setStageKilomitersTotal(kilomitersTotal);
		
		List<StagePoint> stagePoints = new ArrayList<StagePoint>();
		for(int index=0; index<kilomiters.size(); index++){
			stagePoints.add(new StagePoint(kilomiters.get(index), elevation.get(index), longitude.get(index), latitude.get(index)));
		}
		updateMandatoryInformation.setStagePoints(stagePoints);
	
		new RestTemplate().postForObject("http://localhost:" + stagesPort + " /update/mandatoryinformation", updateMandatoryInformation, String.class);
		
		return satgeIdCreated;
	}

	@Override
	public void joinStage(String userId, String stageId) {
		UpdateJoinStageInput updateJoinStageInput = new UpdateJoinStageInput();
		updateJoinStageInput.setStageId(stageId);
		updateJoinStageInput.setMemberId(userId);
		
		new RestTemplate().postForObject("http://localhost:" + stagesPort + " /update/joinstage", updateJoinStageInput, String.class);
	}

	@Override
	public void unjoinStage(String userId, String stageId) {
		UpdateUnjoinStageInput updateUnjoinStageInput = new UpdateUnjoinStageInput();
		updateUnjoinStageInput.setStageId(stageId);
		updateUnjoinStageInput.setMemberId(userId);
		
		new RestTemplate().postForObject("http://localhost:" + stagesPort + " /update/unjoinstage", updateUnjoinStageInput, String.class);
	}

	@Override
	public void delete(String stageId) {
		new RestTemplate().getForObject("http://localhost:"+stagesPort+"/delete/"+stageId, String.class);
	}
}