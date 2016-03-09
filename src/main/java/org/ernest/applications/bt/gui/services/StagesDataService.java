package org.ernest.applications.bt.gui.services;

import java.text.ParseException;
import java.util.List;

import org.ernest.applications.bt.gui.dtos.TeamDto;

public interface StagesDataService {

	TeamDto getStages(String teamId);

	void create(String teamId, String name, String date, int kilomitersTotal, List<Double> kilomiters, List<Double> elevation) throws ParseException;

}
