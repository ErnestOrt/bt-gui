package org.ernest.applications.bt.gui.entities;

public class StagePointDto {

	private Double kilomiter;
	private Double altitude;
	private Double longitude;
	private Double latitude;
	
	public StagePointDto(){}
	
	public StagePointDto(Double kilomiter, Double altitude, Double longitude, Double latitude) {
		this.kilomiter = kilomiter;
		this.altitude = altitude;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Double getKilomiter() {
		return kilomiter;
	}
	public void setKilomiter(Double kilomiter) {
		this.kilomiter = kilomiter;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}
