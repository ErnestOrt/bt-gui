package org.ernest.applications.bt.gui.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.ernest.applications.bt.db.manager.users.ct.UpdateAddBikeInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateAvatarIconInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateDescriptionInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateJoinStageInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateNameInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateRemoveBikeInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateStatisticsInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateUnjoinStageInput;
import org.ernest.applications.bt.db.manager.users.ct.entities.Bike;
import org.ernest.applications.bt.db.manager.users.ct.entities.Statistics;
import org.ernest.applications.bt.db.manager.users.ct.entities.User;
import org.ernest.applications.bt.gui.entities.BikeDto;
import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.StatisticsDto;
import org.ernest.applications.bt.gui.entities.UserDto;
import org.ernest.applications.bt.gui.services.StagesDataService;
import org.ernest.applications.bt.gui.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserDataServiceImpl implements UserDataService{
	
	private static final int MAXIMUM_SKILL_VALUE = 10;
	private static final int MINIMUM_SKILL_VALUE = 1;
	
	@Value("${service.users.port}")
	String usersPort;
	
	@Autowired 
	StagesDataService stagesDataService;

	@Override
	public UserDto getUser(String userId) { 
		User user = new RestTemplate().getForObject("http://localhost:" + usersPort + "/retrieve/"+userId, User.class);
		
		UserDto userDto = new UserDto();
		userDto.setId(userId);
		userDto.setName(user.getName() == null ? "name..." : user.getName());
		userDto.setDescription(user.getDescription() == null ? "description..." : user.getDescription());
		userDto.setAvatarIcon(user.getAvatarIcon());
		userDto.setStatistics(buildStatisticsDto(user.getStatistics()));
		userDto.setStagesIdsJoined(user.getStagesIdsJoined());
		userDto.setBikesList(user.getBikesList().stream()
												.map(bike -> new BikeDto(bike.getId(), bike.getName()))
												.collect(Collectors.toList()));
		return userDto;
	}
	
	@Override
	public void saveName(String userId, String name) {
		UpdateNameInput updateNameInput = new UpdateNameInput();
		updateNameInput.setUserId(userId);
		updateNameInput.setName(name);
		
		new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/name", updateNameInput, String.class);
	}

	@Override
	public void saveDescription(String userId, String description) {
		UpdateDescriptionInput updateDescriptionInput = new UpdateDescriptionInput();
		updateDescriptionInput.setUserId(userId);
		updateDescriptionInput.setDescription(description);
		
		new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/description", updateDescriptionInput, String.class);
	}
	
	@Override
	public void saveAvatarIcon(String userId, String icon) {
		try{
			UpdateAvatarIconInput updateAvatarIconInput = new UpdateAvatarIconInput();
			updateAvatarIconInput.setUserId(userId);
			updateAvatarIconInput.setIconId(Integer.parseInt(icon));
			
			new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/icon", updateAvatarIconInput, String.class);
		}catch(Exception e){ e.printStackTrace();}
	}
	
	@Override
	public void saveSkills(String userId, int resistence, int sprint,
			int montain, int flat, int btt, int road) {
		
		UpdateStatisticsInput updateStatisticsInput = new UpdateStatisticsInput();
		updateStatisticsInput.setUserId(userId);
		
		Statistics statistics = new Statistics();
		statistics.setBtt(validateSkill(btt));
		statistics.setFlat(validateSkill(flat));
		statistics.setMontain(validateSkill(montain));
		statistics.setResistence(validateSkill(resistence));
		statistics.setRoad(validateSkill(road));
		statistics.setSprint(validateSkill(sprint));
		
		updateStatisticsInput.setStatistics(statistics);
		
		new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/statistics", updateStatisticsInput, String.class);
	}
	
	@Override
	public void addBike(String userId, String name) {
		UpdateAddBikeInput updateAddBikeInput = new UpdateAddBikeInput();
		updateAddBikeInput.setUserId(userId);
		Bike bike = new Bike();
		bike.setName(name);
		bike.setId(UUID.randomUUID().toString());
		updateAddBikeInput.setBike(bike);
		
		new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/addbike", updateAddBikeInput, String.class);
	}
	
	@Override
	public void deleteBikes(String userId, List<String> bikesIds) {
		
		bikesIds.forEach(bikesId -> {
			UpdateRemoveBikeInput updateRemoveBikeInput = new UpdateRemoveBikeInput();
			updateRemoveBikeInput.setUserId(userId);
			updateRemoveBikeInput.setBikeId(bikesId);
			
			new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/removebike", updateRemoveBikeInput, String.class);
		});
		
	}
	
	@Override
	public void joinStage(String userId, String stageId) {	
		UpdateJoinStageInput updateUserJoinStageInput = new UpdateJoinStageInput();
		updateUserJoinStageInput.setUserId(userId);
		updateUserJoinStageInput.setStageId(stageId);
		
		new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/joinstage", updateUserJoinStageInput, String.class);
	}

	@Override
	public void unjoinStage(String userId, String stageId) {
		UpdateUnjoinStageInput updateUserUnjoinStageInput = new UpdateUnjoinStageInput();
		updateUserUnjoinStageInput.setUserId(userId);
		updateUserUnjoinStageInput.setStageId(stageId);
		
		new RestTemplate().postForObject("http://localhost:" + usersPort + "/update/unjoinstage", updateUserUnjoinStageInput, String.class);
	}
	
	@Override
	public Map<Integer, Long> getUserStagesJoinnedMap(String userId) {
		List<StageDto> stagesDtoList = new ArrayList<StageDto>();
		
		User user = new RestTemplate().getForObject("http://localhost:" + usersPort + "/retrieve/"+userId, User.class);
		user.getStagesIdsJoined().stream().forEach(stageId -> { stagesDtoList.add(stagesDataService.getStage(stageId)); });
		
		Date futureDate = getDateOffsetMonth(2);
		Date pastDate = getDateOffsetMonth(-4);

		return stagesDtoList.stream()
					 .filter(stage -> stage.getDate().compareTo(futureDate) <= 0 || stage.getDate().compareTo(pastDate) >= 0)
					 .map(stage -> {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(stage.getDate());
								return calendar.get(Calendar.MONTH);})
					 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	private Date getDateOffsetMonth(int offset) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, offset);
		return cal.getTime();
	}

	private int validateSkill(int skillValue) {
		
		skillValue = skillValue < MINIMUM_SKILL_VALUE ? MINIMUM_SKILL_VALUE : skillValue;
		skillValue = skillValue > MAXIMUM_SKILL_VALUE ? MAXIMUM_SKILL_VALUE : skillValue;
		return skillValue;
	}
	
	private StatisticsDto buildStatisticsDto(Statistics statistics) {
		StatisticsDto statisticsDto = new StatisticsDto();
		
		statisticsDto.setBtt(statistics == null ? 0 : statistics.getBtt());
		statisticsDto.setFlat(statistics == null ? 0 : statistics.getFlat());
		statisticsDto.setMontain(statistics == null ? 0 : statistics.getMontain());
		statisticsDto.setResistence(statistics == null ? 0 : statistics.getResistence());
		statisticsDto.setRoad(statistics == null ? 0 : statistics.getRoad());
		statisticsDto.setSprint(statistics == null ? 0 : statistics.getSprint());
		
		return statisticsDto;
	}
}