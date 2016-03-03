package org.ernest.applications.bt.gui.dtos.utils;

import org.ernest.applications.bt.db.manager.users.ct.entities.Statistics;
import org.ernest.applications.bt.gui.dtos.StatisticsDto;

public class DtosUtils {

	public static StatisticsDto buildStatisticsDto(Statistics statistics) {
		StatisticsDto statisticsDto = new StatisticsDto();
		
		statisticsDto.setBtt(statistics == null ? 0 : statistics.getBtt());
		statisticsDto.setFlat(statistics == null ? 0 : statistics.getFlat());
		statisticsDto.setMontain(statistics == null ? 0 : statistics.getMontain());
		statisticsDto.setResistence(statistics == null ? 0 : statistics.getResistence());
		statisticsDto.setRoad(statistics == null ? 0 : statistics.getRoad());
		statisticsDto.setSprint(statistics == null ? 0 : statistics.getSprint());
		
		return statisticsDto;
	}
}
