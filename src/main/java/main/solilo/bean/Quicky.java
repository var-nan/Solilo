package main.solilo.bean;

import main.solilo.utilities.Sentiment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Quicky {
	private String message;
	private String created;
	private boolean visible = true;
	private boolean modified = false;
	private int sentiment;
	
	public Quicky() {
		this.created = getDate();
	}

	public Quicky(String time, String message) {
		this.message = message;
		this.created = time;
		this.sentiment = Sentiment.getSentimentValue(message);
	}

	public Quicky(String message, boolean visibility) {
		this.created = getDate();
		this.message = message;
		this.visible = visibility;
		this.sentiment = Sentiment.getSentimentValue(message);
	}
	
	// constructor only for updating entities
	public Quicky(String time, String message, boolean visibility, boolean modified, int sentiment) {
		this.message = message;
		this.created = time;
		this.visible = visibility;
		this.modified = modified;
		this.sentiment = sentiment;
	}
	
	private String getDate() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

	public int getSentiment() {
		return this.sentiment;
	}

	// I don't this this is needed
	public void setSentiment(int n) {
		this.sentiment = n;
	}

	@Override
	public String toString() {
		return "Quicky [message=" + message + ", created at=" + created+ "]";
	}
	
	
	
}
