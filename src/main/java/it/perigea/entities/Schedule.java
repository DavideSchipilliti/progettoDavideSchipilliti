package it.perigea.entities;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@JsonIgnoreProperties("executed")
public class Schedule implements Serializable{
	
	@Id
	@Column(name="job")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Timestamp creation;
	@Column(nullable = false)
	private Timestamp start;
	@Column
	private Timestamp stop;
	@Enumerated(EnumType.STRING)
	@Column
	private Timespan timespan;
	@Column
	private Integer multiplier;
	@Column(name = "forex_ticker")
	private String forexTicker;
	@OneToMany (mappedBy = "job", cascade = CascadeType.ALL)
	private List<Run> executed;
	
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
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getStop() {
		return stop;
	}
	public void setStop(Timestamp stop) {
		this.stop = stop;
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
}
