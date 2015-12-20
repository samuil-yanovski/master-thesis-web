package models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;
import com.avaje.ebean.annotation.Encrypted; 

@Entity
@SuppressWarnings("serial")
public class Credentials extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	@Required
	@Column(unique=true)
	private String email;
	
	@Basic
	@Encrypted(dbEncryption=false)
	private String password;
	
	@Basic
	private String googlePlusId;
	
	@Basic
	private String facebookId;
	
    @Basic
	@ManyToMany(cascade=CascadeType.ALL)
    List<Event> events;
    
    @Basic
	@ManyToOne(cascade=CascadeType.ALL)
	private List<Device> devices;
	
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
