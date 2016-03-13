package org.ernest.applications.bt.gui.entities;

import java.util.List;
import java.util.Set;

public class UserDto {

	private String id;
	private String name;
	private int avatarIcon;
	private String description;
	private StatisticsDto statistics;
	private List<BikeDto> bikesList;
	private Set<String> stagesIdsJoined;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAvatarIcon() {
		return avatarIcon;
	}
	public void setAvatarIcon(int avatarIcon) {
		this.avatarIcon = avatarIcon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatisticsDto getStatistics() {
		return statistics;
	}
	public void setStatistics(StatisticsDto statistics) {
		this.statistics = statistics;
	}
	public List<BikeDto> getBikesList() {
		return bikesList;
	}
	public void setBikesList(List<BikeDto> bikesList) {
		this.bikesList = bikesList;
	}
	public Set<String> getStagesIdsJoined() {
		return stagesIdsJoined;
	}
	public void setStagesIdsJoined(Set<String> stagesIdsJoined) {
		this.stagesIdsJoined = stagesIdsJoined;
	}
}