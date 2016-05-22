package models;

import com.avaje.ebean.annotation.Encrypted;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.OneToOne;

@Entity
@SuppressWarnings("serial")
public class Credentials extends Model {

	@Id
	private Long key;

	@Basic
	@Required
	@Column(unique=true)
	private String email;
	
	@Basic
	@JsonIgnore
	private String password;
	
	@Basic
	private String googlePlusId;
	
	@Basic
	private String facebookId;
	
	@OneToOne
    private Student student;
	
	@OneToOne
    private Teacher teacher;
	
    @Basic
	@ManyToMany(cascade=CascadeType.ALL)
	@JsonIgnore
    private List<Event> events;
    
    @Basic
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Device> devices;
	
	public static Finder<Long,Credentials> find = new Finder<Long,Credentials>(
        Long.class, Credentials.class
    ); 
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGooglePlusId() {
		return googlePlusId;
	}

	public void setGooglePlusId(String googlePlusId) {
		this.googlePlusId = googlePlusId;
	}
	
	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	@Override
	public String toString() {
		return "Credentials [key=" + key + ", email=" + email + "]";
	}
}
