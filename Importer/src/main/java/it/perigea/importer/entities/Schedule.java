package it.perigea.importer.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import it.perigea.importer.enums.State;
import it.perigea.importer.enums.Timespan;
import it.perigea.importer.enums.TypeOfRequest;

@Entity
public class Schedule implements Serializable{
	
	private static final long serialVersionUID = -4323015840387512707L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Timestamp creation;
	@Enumerated(EnumType.STRING)
	@Column(name = "type_of_request")
	private TypeOfRequest typeOfRequest;
	@Column
	private Timestamp date1;
	@Column
	private Timestamp date2;
	@Enumerated(EnumType.STRING)
	@Column
	private Timespan timespan;
	@Column
	private Integer multiplier;
	@Column(name = "forex_ticker")
	private String forexTicker;
	@OneToMany (mappedBy = "schedule", cascade = CascadeType.ALL)
	private List<Run> executed;
	@Column(name = "cron_expression")
	private String cronString;
	@Enumerated(EnumType.STRING)
	@Column
	private State state;
	
	public Schedule() {
		super();
		this.executed=new ArrayList<>();
	}
	
	//Getter and Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<Run> getExecuted() {
		return executed;
	}
	public void setExecuted(List<Run> executed) {
		this.executed = executed;
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getCronString() {
		return cronString;
	}
	public void setCronString(String cronString) {
		this.cronString = cronString;
	}
}
