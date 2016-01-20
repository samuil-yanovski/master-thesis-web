package models;

import org.joda.time.DateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.utils.dao.BasicModel;

@Entity
@SuppressWarnings("serial")
public class GraduationDate extends Model implements BasicModel<Long> {

	@Id
	private Long key;

	@Basic
	@Required
	private String name;
	
	@Basic
	@Required
	private DateTime date;
	
	public static Finder<Long,GraduationDate> find = new Finder<Long,GraduationDate>(
        Long.class, GraduationDate.class
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
	
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "GraduationDate [key=" + key + ", name=" + name + "]";
	}
}
