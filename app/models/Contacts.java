package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Contacts extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	private String skype;
	
	@Basic
	private String phone;
	
	@Basic
	private String email;
	
	public static Finder<Long,Contacts> find = new Finder<Long,Contacts>(
        Long.class, Contacts.class
    ); 
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contacts [key=" + key + ", skype=" + skype + ", phone=" + phone + "]";
	}
}
