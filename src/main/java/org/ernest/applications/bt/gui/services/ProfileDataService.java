package org.ernest.applications.bt.gui.services;

import org.ernest.applications.bt.gui.dtos.UserDto;

public interface ProfileDataService {

	UserDto getUser(String userId);

	void saveName(String userId, String name);
	
	void saveDescription(String userId, String description);

	void saveSkills(String userIdTest, int resistence, int sprint,
			int montain, int flat, int btt, int road);
}
