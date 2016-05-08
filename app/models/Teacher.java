package models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SuppressWarnings("serial")
public class Teacher extends Model {

	@Id
	private Long key;

	@Basic
	@Required
	private String name;
	
	@Basic
	private String avatar;
	
	@Basic
	@OneToOne
	private Contacts contacts;
	
	@Basic
	@OneToOne
	@JsonIgnore
	private Credentials credentials;
	
	@Basic
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Thesis> theses;
	
	@Basic
	@ManyToOne(cascade=CascadeType.ALL)
	private List<Interest> interests;
	
	public static Finder<Long,Teacher> find = new Finder<Long,Teacher>(
        Long.class, Teacher.class
    ); 
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public List<Thesis> getTheses() {
		return theses;
	}

	public void setTheses(List<Thesis> theses) {
		this.theses = theses;
	}
	
	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
	
	@Override
	public String toString() {
		return "Teacher [key=" + key + ", name=" + name + "]";
	}
}
