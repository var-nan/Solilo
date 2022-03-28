package main.solilo.entity;

import javax.persistence.*;

import main.solilo.bean.Quicky;


@NamedQueries({
		@NamedQuery(
				name = "extractTodaySentimentAverage",
				query = "SELECT AVG(q.sentiment) FROM QuickyEntity q WHERE " +
						"DAY(q.created)=?1 and MONTH(q.created)=?2 and YEAR(q.created)=?3"
		),
		@NamedQuery(
				name="extractTodayQuickies",
				query="SELECT k FROM QuickyEntity k WHERE " +
						"DAY(k.created)=?1 and MONTH(k.created)=?2 and YEAR(k.created)=?3"
		),
		@NamedQuery(
				name="extractLatestQuickies",
				query="SELECT k FROM QuickyEntity k ORDER BY k.created DESC"
		)
})

@Entity 
@Table(name="quicky")
public class QuickyEntity {
	
	@Id
	private String created;
	
	@Column(name = "message")
	private String message;
	private boolean visible = true;
	private boolean modified = false;
	private int sentiment;
	
	// constructor
	public QuickyEntity(Quicky quicky) {
		this.created = quicky.getCreated();
		this.message = quicky.getMessage();
		this.visible = quicky.isVisible();
		this.modified = quicky.isModified();
		this.sentiment = quicky.getSentiment();
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

	public int getSentiment() {
		return this.sentiment;
	}

	public void setSentiment(int n) {
		this.sentiment = n;
	}

	@Override
	public String toString() {
		return "Quicky [message=" + message + ", created at=" + created + "]";
	}
}
