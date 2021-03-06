package models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SuppressWarnings("serial")
public class Thesis extends Model {

	@Id
	private Long key;

	@Basic
	@Required
	private String title;
	
	@Basic
	@Required
	private String description;
	
	@Basic
	private boolean approved = true;
	
	@Basic
	@OneToOne
	@JsonIgnore
	private Category category;
	
	@Basic
	@OneToOne
	private Teacher author;
	
	@Basic
	@OneToOne
	private Student graduate;
	
	public static Finder<Long,Thesis> find = new Finder<Long,Thesis>(
        Long.class, Thesis.class
    ); 
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Teacher getAuthor() {
		return author;
	}

	public void setAuthor(Teacher author) {
		this.author = author;
	}
	
	public Student getGraduate() {
		return graduate;
	}

	public void setGraduate(Student graduate) {
		this.graduate = graduate;
	}
	
	@Override
	public String toString() {
		return "Thesis [key=" + key + ", title=" + title + "]";
	}
}
