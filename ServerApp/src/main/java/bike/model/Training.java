package bike.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;



@Entity()
//@NamedEntityGraph(name = "GroupInfo.detail", attributeNodes = @NamedAttributeNode("trainingPoints"))
@Table(name = "TRAINING")
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Timestamp date;
	private float avgSpeed;
	
	private float totalDistance;
	private float duration;
	private int climb;
	private int downhill;
	private float avgBpm;
	
	@OneToMany(mappedBy="training")
    //    @JsonManagedReference
    @OrderBy("id ASC")
	private Set<TrainingPoint> trainingPoints;
	
//	Constructors, getters and setters
	public Training(Timestamp date, float avgSpeed, float totalDistance, float duration, int climb, int downhill,
			float avgBpm) {
		super();
		this.date = date;
		this.avgSpeed = avgSpeed;
		
		this.totalDistance = totalDistance;
		this.duration = duration;
		this.climb = climb;
		this.downhill = downhill;
		this.avgBpm = avgBpm;
	}
        
        public Training() {
		
	}
        
	public Training(Timestamp date) {
		this.date = date;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public float getAvgSpeed() {
		return avgSpeed;
	}
	public void setAvgSpeed(float avgSpeed) {
		this.avgSpeed = avgSpeed;
	}
	public float getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(float totalDistance) {
		this.totalDistance = totalDistance;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public int getClimb() {
		return climb;
	}
	public void setClimb(int climb) {
		this.climb = climb;
	}
	public int getDownhill() {
		return downhill;
	}
	public void setDownhill(int downhill) {
		this.downhill = downhill;
	}
	public float getAvgBpm() {
		return avgBpm;
	}
	public void setAvgBpm(float avgBpm) {
		this.avgBpm = avgBpm;
	}
	public Set<TrainingPoint> getTrainingPoints() {
		return trainingPoints;
	}
	public void setTrainingPoints(Set<TrainingPoint> trainingPoints) {
		this.trainingPoints = trainingPoints;
	}
	public long getId() {
		return id;
	}	
}
