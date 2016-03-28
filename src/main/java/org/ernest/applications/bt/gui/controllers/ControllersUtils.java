package org.ernest.applications.bt.gui.controllers;

import org.ernest.applications.bt.gui.entities.StageDto;
import org.ernest.applications.bt.gui.entities.StagePointDto;

public class ControllersUtils {

	public static StageDto buildEmptyStage() {
		StageDto stageDto = new StageDto();
		stageDto.setName("Join a team or create a stage...");
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		stageDto.getStagePoints().add(new StagePointDto(0.0, 0.0, 0.0, 0.0));
		return stageDto;
	}
}
