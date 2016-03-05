package org.ernest.applications.bt.gui.dtos;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StageDto {

	private String id;
	private String name;
	private Date date;
	private int kilomitersTotal;
	private Map<Integer, Integer> altitudeByKilomiter;
	private Set<String> joinedMembers;
	
	public StageDto(){
		joinedMembers = new HashSet<String>();
		altitudeByKilomiter = new HashMap<Integer, Integer>();
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
	public Map<Integer, Integer> getAltitudeByKilomiter() {
		return altitudeByKilomiter;
	}
	public void setAltitudeByKilomiter(Map<Integer, Integer> altitudeByKilomiter) {
		this.altitudeByKilomiter = altitudeByKilomiter;
	}
}