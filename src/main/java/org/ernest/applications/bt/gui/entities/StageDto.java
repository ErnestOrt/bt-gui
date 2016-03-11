package org.ernest.applications.bt.gui.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StageDto {

	private String id;
	private String name;
	private Date date;
	private int kilomitersTotal;
	private List<StagePointDto> stagePoints;
	private Set<String> joinedMembers;
	
	public StageDto(){
		joinedMembers = new HashSet<String>();
		stagePoints = new ArrayList<StagePointDto>();
	}
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Set<String> getJoinedMembers() {
		return joinedMembers;
	}
	public void setJoinedMembers(Set<String> joinedMembers) {
		this.joinedMembers = joinedMembers;
	}
	public int getKilomitersTotal() {
		return kilomitersTotal;
	}
	public void setKilomitersTotal(int kilomitersTotal) {
		this.kilomitersTotal = kilomitersTotal;
	}
	public List<StagePointDto> getStagePoints() {
		return stagePoints;
	}
	public void setStagePoints(List<StagePointDto> stagePoints) {
		this.stagePoints = stagePoints;
	}
}