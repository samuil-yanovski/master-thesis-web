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

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.format.*;

@Entity
@SuppressWarnings("serial")
public class Event extends Model implements BasicModel<Long> {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_gen")
    @SequenceGenerator(name = "task_gen", sequenceName = "task_id_seq")
	private Long key;

	@Basic
	@Required
	private String name;
	
	@Basic
	@Required
	@Formats.DateTime(pattern="yyyy-MM-dd")
	private DateTime date;
	
	@ManyToMany(cascade=CascadeType.ALL)
    private List<Credentials> members;
    
    public static Finder<Long,Event> find = new Finder<Long,Event>(
        Long.class, Event.class
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
