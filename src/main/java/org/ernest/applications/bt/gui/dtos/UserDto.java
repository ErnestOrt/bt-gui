package org.ernest.applications.bt.gui.dtos;

import java.util.List;

public class UserDto {

	private String name;
	private String description;
	private StatisticsDto statistics;
	private List<BikeDto> bikesList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}