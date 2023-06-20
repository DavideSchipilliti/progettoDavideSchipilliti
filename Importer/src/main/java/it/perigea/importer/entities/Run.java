package it.perigea.importer.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Run implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable=false)
	private Timestamp started;
	@Column (nullable=false)
	private Timestamp finished;
	@Column (nullable=false)
	private String status;
	//@ManyToOne (cascade = CascadeType.ALL)
	//@JoinColumn (name = "SCHEDULE_ID", nullable=false)
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
	@Column (name = "SCHEDULE_ID", nullable=false)
	private Long schedule;
	
	public Run() {
		super();
	}
	
	public Run(Timestamp started, Timestamp finished, String status, Long schedule) {
		this.started=started;
		this.finished=finished;
		this.status=status;
		this.schedule=schedule;
	}
	
	//Getter and Setter
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
	public void setSchedule(Long job) {
		this.schedule = job;
	}
}
