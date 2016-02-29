package org.ernest.applications.bt.gui.services;

import java.util.List;

import org.ernest.applications.bt.gui.dtos.UserDto;

public interface ProfileDataService {

	UserDto getUser(String userId);

	void saveName(String userId, String name);
	
	void saveDescription(String userId, String description);

	void saveSkills(String userIdTest, int resistence, int sprint,
			int montain, int flat, int btt, int road);

	void addBike(String userIdStatic, String name);

	void deleteBikes(String userId, List<String> bikesIds);
}
