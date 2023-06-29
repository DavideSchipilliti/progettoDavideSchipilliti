package it.perigea.importer.dto;

import java.sql.Timestamp;

public class RunDTO {
	
	private Long id;
	private Timestamp started;
	private Timestamp finished;
	private String status;
	private Long schedule;
	
	public RunDTO() {
		super();
	}
	
	public RunDTO(Timestamp started, Timestamp finished, String status, Long schedule) {
		this.started=started;
		this.finished=finished;
		this.status=status;
		this.schedule=schedule;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getStarted() {
		return started;
	}
	public void setStarted(Timestamp started) {
		this.started = started;
	}
	public Timestamp getFinished() {
		return finished;
	}
	public void setFinished(Timestamp finished) {
		this.finished = finished;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getSchedule() {
		return schedule;
	}
	public void setSchedule(Long schedule) {
		this.schedule = schedule;
	}
}
