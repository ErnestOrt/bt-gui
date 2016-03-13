package org.ernest.applications.bt.gui.entities;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {

	private String name;
	private List<UserDto> members;
	private List<StageDto> stages;
	private List<CommentDto> comments;
	
	public TeamDto(){
		members = new ArrayList<UserDto>();
		stages = new ArrayList<StageDto>();
		comments = new ArrayList<CommentDto>();
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
	public List<CommentDto> getComments() {
		return comments;
	}
	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
}