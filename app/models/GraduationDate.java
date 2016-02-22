package models;

import org.joda.time.DateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
public class GraduationDate extends Model {

	@Id
	private Long key;

	@Basic
	@Required
	private String name;
	
	@Basic
	@Required
	private DateTime day;
	
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
	
	public DateTime getDay() {
		return day;
	}

	public void setDay(DateTime day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return "GraduationDate [key=" + key + ", name=" + name + "]";
	}
}
