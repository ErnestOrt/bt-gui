package org.ernest.applications.bt.gui.services;

import java.util.stream.Collectors;

import org.ernest.applications.bt.db.manager.users.ct.UpdateDescriptionInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateNameInput;
import org.ernest.applications.bt.db.manager.users.ct.UpdateStatisticsInput;
import org.ernest.applications.bt.db.manager.users.ct.entities.Statistics;
import org.ernest.applications.bt.db.manager.users.ct.entities.User;
import org.ernest.applications.bt.gui.dtos.StatisticsDto;
import org.ernest.applications.bt.gui.dtos.UserDto;
import org.ernest.applications.bt.gui.dtos.BikeDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProfileDataServiceImpl implements ProfileDataService{

	private static final int MAXIMUM_SKILL_VALUE = 10;
	private static final int MINIMUM_SKILL_VALUE = 1;

	@Override
	public UserDto getUser(String userId) { 
		User user = new RestTemplate().getForObject("http://localhost:9999/retrieve/"+userId, User.class);
		
		UserDto userDto = new UserDto();
		userDto.setName(user.getName() == null ? "name..." : user.getName());
		userDto.setDescription(user.getDescription() == null ? "description..." : user.getDescription());
		userDto.setStatistics(buildStatisticsDto(user.getStatistics()));
		userDto.setBikesList(user.getBikesList().stream()
												.map(bike -> new BikeDto(bike.getId(), bike.getName(), bike.getImage()))
												.collect(Collectors.toList()));
		return userDto;
	}
	
	@Override
	public void saveName(String userId, String name) {
		UpdateNameInput updateNameInput = new UpdateNameInput();
		updateNameInput.setUserId(userId);
		updateNameInput.setName(name);
		
		new RestTemplate().postForObject("http://localhost:9999/update/name", updateNameInput, String.class);
	}

	@Override
	public void saveDescription(String userId, String description) {
		UpdateDescriptionInput updateDescriptionInput = new UpdateDescriptionInput();
		updateDescriptionInput.setUserId(userId);
		updateDescriptionInput.setDescription(description);
		
		new RestTemplate().postForObject("http://localhost:9999/update/description", updateDescriptionInput, String.class);
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
		
		new RestTemplate().postForObject("http://localhost:9999/update/statistics", updateStatisticsInput, String.class);
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