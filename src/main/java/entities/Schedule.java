package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long job;
	@Column(nullable = false)
	private Integer start;
	@Column
	private Integer stop;
	@Column
	private Timespan timespan;
	@Column
	private Integer multiplier;
	@Column(name = "forex_ticker")
	private String forexTicker;
	@OneToMany (mappedBy = "run", cascade = CascadeType.ALL)
	List<Run> executed;
	
	public Schedule() {
		super();
		this.executed=new ArrayList<>();
	}
	
	
	//Getter and Setter
	public Long getJob() {
		return job;
	}
	public void setJob(Long job) {
		this.job = job;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getStop() {
		return stop;
	}
	public void setStop(Integer stop) {
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
	
}
