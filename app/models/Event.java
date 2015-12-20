package models;

import org.joda.time.DateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class Event extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	@Required
	private String name;
	
	@Basic
	@Required
	private DateTime date;
	
	@ManyToMany(cascade=CascadeType.ALL)
    List<Credentials> members;
	
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
	
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}


	public List<Credentials> getMembers() {
		return members;
	}

	public void setMembers(List<Credentials> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return "Event [key=" + key + ", name=" + name + "]";
	}
}
