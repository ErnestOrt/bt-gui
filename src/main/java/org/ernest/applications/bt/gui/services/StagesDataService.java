package org.ernest.applications.bt.gui.services;

import java.text.ParseException;
import java.util.List;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.TeamDto;

public interface StagesDataService {
	
	StageDto getStage(String stageId);

	String create(String name, String date, int kilomitersTotal, List<Double> kilomiters, List<Double> elevation, List<Double> longitude, List<Double> latitude) throws ParseException;

	void joinStage(String userId, String stageId);

	void unjoinStage(String userId, String stageId);

	void delete(String stageId);

}
