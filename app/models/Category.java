package models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@SuppressWarnings("serial")
public class Category extends Model {

	@Id
	private Long key;

	@Basic
	@Required
	private String name;
	
	@Basic
	@ManyToOne(cascade=CascadeType.ALL)
	private List<Thesis> theses;
	
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
	
	public List<Thesis> getTheses() {
		return theses;
	}

	public void setTheses(List<Thesis> theses) {
		this.theses = theses;
	}
	
	@Override
	public String toString() {
		return "Category [key=" + key + ", name=" + name + "]";
	}
}
