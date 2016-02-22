package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
public class Device extends Model {

	@Id
	private Long key;

	@Basic
	@Required
	@Column(unique=true)
	private String token;
	
	@Basic
	@OneToOne
	private Credentials owner;
	
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
