package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.OneToOne;

@Entity
@SuppressWarnings("serial")
public class Interest extends Model {

	@Id
	private Long key;

	@Basic
	private String name;
	
	@Basic
	@OneToOne
	@JsonIgnore
	private Teacher teacher;
	
	public static Finder<Long,Interest> find = new Finder<Long,Interest>(
        Long.class, Interest.class
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String toString() {
		return "Interest [key=" + key + ", name=" + name + "]";
	}
}
