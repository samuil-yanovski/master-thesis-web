package models;

import org.joda.time.DateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Token extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	@Required
	private String auth;
		
	@Basic
	@Required
	private String refresh;
	
	@Basic
	@Required
	private DateTime expirationDate;
	
	@OneToOne
    Credentials owner;
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	
	public DateTime getDate() {
		return expirationDate;
	}

	public void setExpirationDate(DateTime expirationDate) {
		this.expirationDate = expirationDate;
	}


	public Credentials getOwner() {
		return owner;
	}

	public void setOwner(Credentials owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "Token [key=" + key + ", auth=" + auth + "]";
	}
}
