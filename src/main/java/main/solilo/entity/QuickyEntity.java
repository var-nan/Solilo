package main.solilo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import main.solilo.bean.Quicky;

@Entity 
@Table(name="quicky")
public class QuickyEntity {
	
	@Id
	private String created;
	
	@Column(name = "message")
	private String message;
	private boolean visible = true;
	private boolean modified = false;
	
	// constructor
	public QuickyEntity(Quicky quicky) {
		this.created = quicky.getCreated();
		this.message = quicky.getMessage();
		this.visible = quicky.isVisible();
		this.modified = quicky.isModified();
	}
	
	// TODO add default constructor, and set created method
	public QuickyEntity() {
		// no need to do anything
	}
	
	/*
	private String getDate() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return sdf.format(now);
	}
	*/
	
	// get the date as string when returning from database

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisibile(boolean visibility) {
		this.visible = visibility;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "Quicky [message=" + message + ", created at=" + created + "]";
	}
}
