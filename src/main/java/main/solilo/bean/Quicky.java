package main.solilo.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Quicky {
	private String message;
	private String created;
	private boolean visible = true;
	private boolean modified = false;
	
	public Quicky() {
		this.created = getDate();
	}

	public Quicky(String time, String message) {
		this.message = message;
		this.created = time;
	}

	public Quicky(String message, boolean visibility) {
		this.created = getDate();
		this.message = message;
		this.visible = visibility;
	}
	
	// constructor only for updating entities
	public Quicky(String time, String message, boolean visibility, boolean modified) {
		this.message = message;
		this.created = time;
		this.visible = visibility;
		this.modified = modified;
	}
	
	private String getDate() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return sdf.format(now);
	}
	

	public String getCreated() {
		return this.created;
	}
	
	public void setCreated(String timeStamp) {
		this.created = timeStamp;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visibility) {
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
		return "Quicky [message=" + message + ", created at=" + created+ "]";
	}
	
	
	
}
