package models;

import org.joda.time.DateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Device extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	@Required
	@Column(unique=true)
	private String token;
	
	@Basic
	@OneToOne
	private Credentials owner;
	
	public static Finder<Long,Device> find = new Finder<Long,Device>(
        Long.class, Device.class
    ); 
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Credentials getOwner() {
		return owner;
	}

	public void setOwner(Credentials owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "Device [key=" + key + ", token=" + token + "]";
	}
}
