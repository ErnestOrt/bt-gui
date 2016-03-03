package org.ernest.applications.bt.gui.services;

import org.ernest.applications.bt.db.manager.teams.ct.entities.Team;
import org.ernest.applications.bt.db.manager.users.ct.entities.User;
import org.ernest.applications.bt.gui.dtos.TeamDto;
import org.ernest.applications.bt.gui.dtos.UserDto;
import org.ernest.applications.bt.gui.dtos.utils.DtosUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MembersDataServiceImpl implements MembersDataService {

	@Override
	public TeamDto getMembers(String teamId) {
		
		Team team = new RestTemplate().getForObject("http://localhost:7777/retrieve/"+teamId, Team.class);
		
		TeamDto teamDto = new TeamDto();
		teamDto.setName(team.getName());
		
		team.getMembersIds().forEach(memberId -> {
			User user = new RestTemplate().getForObject("http://localhost:9999/retrieve/"+memberId, User.class);
			
			UserDto userDto = new UserDto();
			userDto.setId(memberId);
			userDto.setName(user.getName() == null ? "name..." : user.getName());
			userDto.setStatistics(DtosUtils.buildStatisticsDto(user.getStatistics()));
			teamDto.getMembers().add(userDto); 
		});

		return teamDto;
	}
}