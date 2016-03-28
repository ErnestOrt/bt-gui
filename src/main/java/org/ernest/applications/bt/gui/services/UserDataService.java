package org.ernest.applications.bt.gui.services;

import java.util.List;
import java.util.Map;

import org.ernest.applications.bt.gui.entities.UserDto;

public interface UserDataService {

	UserDto getUser(String userId);

	void saveName(String userId, String name);
	
	void saveDescription(String userId, String description);

	void saveSkills(String userIdTest, int resistence, int sprint, int montain, int flat, int btt, int road);

	void addBike(String userId, String name);

	void deleteBikes(String userId, List<String> bikesIds);
	
	void joinStage(String userId, String stageId);

	void unjoinStage(String userId, String stageId);

	Map<Integer, Long> getUserStagesJoinnedMap(String userId);

	void saveAvatarIcon(String userId, String icon);

	void create(String token, String mail);

	void joinTeam(String teamId, String userId);

	void unjoinTeam(String teamId, String userId);
}
