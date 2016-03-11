package org.ernest.applications.bt.gui.entities;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {

	private String name;
	private List<UserDto> members;
	private List<StageDto> stages;
	
	public TeamDto(){
		members = new ArrayList<UserDto>();
		stages = new ArrayList<StageDto>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UserDto> getMembers() {
		return members;
	}
	public void setMembers(List<UserDto> members) {
		this.members = members;
	}
	public List<StageDto> getStages() {
		return stages;
	}
	public void setStages(List<StageDto> stages) {
		this.stages = stages;
	}
}