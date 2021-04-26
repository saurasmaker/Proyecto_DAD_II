package edu.ucam.pojos;

import java.sql.Date;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;

import edu.ucam.interfaces.IMyPojo;

public class Assessment implements IMyPojo{
	
	/*
	 * Static Attributes
	 */
	public static final String ATR_ASSESSMENT = "ATR_ASSESSMENT", ATR_ASSESSMENT_ID = "ATR_ASSESSMENT_ID", ATR_ASSESSMENT_VALUE = "ATR_ASSESSMENT_VALUE",
			ATR_ASSESSMENT_SUBJECT = "ATR_ASSESSMENT_SUBJECT", ATR_ASSESSMENT_COMMENT = "ATR_ASSESSMENT_COMMENT",
			ATR_ASSESSMENT_PUBLICATIONDATE = "ATR_ASSESSMENT_PUBLICATIONDATE", ATR_ASSESSMENT_PUBLICATIONTIME = "ATR_ASSESSMENT_PUBLICATIONTIME",
			ATR_ASSESSMENT_EDITDATE = "ATR_ASSESSMENT_EDITDATE", ATR_ASSESSMENT_EDITTIME = "ATR_ASSESSMENT_EDITTIME",
			ATR_ASSESSMENT_USERID = "ATR_ASSESSMENT_USERID", ATR_ASSESSMENT_VIDEOGAMEID = "ATR_ASSESSMENT_VIDEOGAMEID",
			ATR_ASSESSMENTS_LIST = "ATR_ASSESSMENTS_LIST";
	
	
	
	/*
	 * Attributes
	 */
	private String id, subject, comment, videogameId, userId;
	private int value;
	private Date publicationDate, editDate;
	private Time publicationTime, editTime;
	
	/*
	 * Constructors
	 */
	public Assessment() {
		
	}	
	
	public Assessment(HttpServletRequest request) {
		this.id = request.getParameter(ATR_ASSESSMENT_ID);
		this.value = Integer.parseInt(request.getParameter(ATR_ASSESSMENT_VALUE));
		this.subject = request.getParameter(ATR_ASSESSMENT_SUBJECT);
		this.comment = request.getParameter(ATR_ASSESSMENT_COMMENT);
		this.videogameId = request.getParameter(ATR_ASSESSMENT_VIDEOGAMEID);
		this.userId = request.getParameter(ATR_ASSESSMENT_USERID);
		this.publicationDate = Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONDATE));
		this.publicationTime = Time.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_PUBLICATIONTIME));
		this.editDate = Date.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITDATE));
		this.editTime = Time.valueOf(request.getParameter(Assessment.ATR_ASSESSMENT_EDITTIME));
	}
	
	/*
	 * Methods
	 */
	@Override
	public String toJavaScriptFunction(){
		return "'" + this.id + "', '" + this.value + "', '" + this.subject + "', '" + this.comment + "', '"  + 
				this.publicationDate + "', '" + this.publicationTime + "', '" + this.editDate + "', '" +
				this.editTime + "', '" + this.videogameId + "', '" + this.userId + "'";
	}
	
		
	/*
	 * Getters & Setters
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getVideogameId() {
		return videogameId;
	}
	public void setVideogameId(String videogameId) {
		this.videogameId = videogameId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	public Time getPublicationTime() {
		return publicationTime;
	}
	public void setPublicationTime(Time publicationTime) {
		this.publicationTime = publicationTime;
	}
	
	public Time getEditTime() {
		return editTime;
	}
	public void setEditTime(Time editTime) {
		this.editTime = editTime;
	}
	
	
	
}
