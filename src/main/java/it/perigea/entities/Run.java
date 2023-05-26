package it.perigea.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Run {

	@Id
	private String id;
	@Column (nullable=false)
	private Integer started;
	@Column (nullable=false)
	private Integer finished;
	@Column (nullable=false)
	private String status;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "JOB_ID", nullable=false)
	private Schedule job;
	
	//Getter and Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getStarted() {
		return started;
	}
	public void setStarted(Integer started) {
		this.started = started;
	}
	public Integer getFinished() {
		return finished;
	}
	public void setFinished(Integer finished) {
		this.finished = finished;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Schedule getJob() {
		return job;
	}
	public void setJob(Schedule job) {
		this.job = job;
	}
	
}
