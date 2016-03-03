package org.ernest.applications.bt.gui.dtos;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {

	private String name;
	private List<UserDto> members;
	
	public TeamDto(){
		members = new ArrayList<UserDto>();
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
}