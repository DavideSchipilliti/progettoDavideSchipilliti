package it.perigea.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@SuppressWarnings("serial")
@Entity
public class Run implements Serializable{

	@Id
	private String id;
	@Column (nullable=false)
	private Timestamp started;
	@Column (nullable=false)
	private Timestamp finished;
	@Column (nullable=false)
	private String status;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "JOB_ID", nullable=false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
	private Schedule job;
	
	public Run() {
		super();
	}
	
	public Run(String id, Timestamp started, Timestamp finished, String status, Schedule job) {
		this.id=id;
		this.started=started;
		this.finished=finished;
		this.status=status;
		this.job=job;
	}
	
	//Getter and Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public Schedule getJob() {
		return job;
	}
	public void setJob(Schedule job) {
		this.job = job;
	}
}
