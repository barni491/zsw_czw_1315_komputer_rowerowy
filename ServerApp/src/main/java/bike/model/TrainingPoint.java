package bike.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRAINING_POINT")
public class TrainingPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Timestamp time;
	private float altitude;
	private float longitude;
	private float latitude;
	private float speed;
	private float bpm;
	private float temperature;
	private float humidity;
	
	@ManyToOne
	private Training training;
	
//	Constructors, getters and setters
	public TrainingPoint(Timestamp time, float altitude, float longitude, float latitude, float speed, float bpm,
			float temperature, float humidity) {
		super();
		this.time = time;
		this.altitude = altitude;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speed = speed;
		this.bpm = bpm;
		this.temperature = temperature;
		this.humidity = humidity;
	}
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public float getAltitude() {
		return altitude;
	}
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getBpm() {
		return bpm;
	}
	public void setBpm(float bpm) {
		this.bpm = bpm;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public long getId() {
		return id;
	}
}
