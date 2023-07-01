package it.perigea.importer.dto;

import java.sql.Timestamp;

import it.perigea.importer.entities.enums.State;
import it.perigea.importer.entities.enums.Timespan;
import it.perigea.importer.entities.enums.TypeOfRequest;

public class ScheduleDTO {

	private Long id;
	private Timestamp creation;
	private TypeOfRequest typeOfRequest;
	private Timestamp date1;
	private Timestamp date2;
	private Timespan timespan;
	private Integer multiplier;
	private String forexTicker;
	private String cronString;
	private State state;

	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getCreation() {
		return creation;
	}
	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}
	public TypeOfRequest getTypeOfRequest() {
		return typeOfRequest;
	}
	public void setTypeOfRequest(TypeOfRequest typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}
	public Timestamp getDate1() {
		return date1;
	}
	public void setDate1(Timestamp date1) {
		this.date1 = date1;
	}
	public Timestamp getDate2() {
		return date2;
	}
	public void setDate2(Timestamp date2) {
		this.date2 = date2;
	}
	public Timespan getTimespan() {
		return timespan;
	}
	public void setTimespan(Timespan timespan) {
		this.timespan = timespan;
	}
	public Integer getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(Integer multiplier) {
		this.multiplier = multiplier;
	}
	public String getForexTicker() {
		return forexTicker;
	}
	public void setForexTicker(String forexTicker) {
		this.forexTicker = forexTicker;
	}
	public String getCronString() {
		return cronString;
	}
	public void setCronString(String cronString) {
		this.cronString = cronString;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
