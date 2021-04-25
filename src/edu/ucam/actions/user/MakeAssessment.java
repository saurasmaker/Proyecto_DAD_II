package edu.ucam.actions.user;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucam.daos.AssessmentDAO;
import edu.ucam.enums.ErrorType;
import edu.ucam.enums.SearchBy;
import edu.ucam.interfaces.IAction;
import edu.ucam.pojos.Assessment;
import edu.ucam.pojos.Videogame;

public class MakeAssessment implements IAction{
	public static final String ATR_ACTION = "ATR_MAKEASSESSMENT_ACTION";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Assessment assessment;
		
		try {
			assessment = (new AssessmentDAO()).read(request.getParameter(Assessment.ATR_ASSESSMENT_ID), SearchBy.ID);
			
			if(assessment != null) {
				
				assessment.setValue(Integer.parseInt(request.getParameter(Assessment.ATR_ASSESSMENT_VALUE)));
				assessment.setSubject(request.getParameter(Assessment.ATR_ASSESSMENT_SUBJECT));
				assessment.setComment(request.getParameter(Assessment.ATR_ASSESSMENT_COMMENT));
				assessment.setEditDate(Date.valueOf(LocalDate.now()));
				assessment.setEditTime(Time.valueOf(LocalTime.now()));				
				
				(new AssessmentDAO()).update(assessment.getId(), SearchBy.ID, assessment);
	
			}
			else {
				assessment = new Assessment();
				assessment.setValue(Integer.parseInt(request.getParameter(Assessment.ATR_ASSESSMENT_VALUE)));
				assessment.setSubject(request.getParameter(Assessment.ATR_ASSESSMENT_SUBJECT));
				assessment.setComment(request.getParameter(Assessment.ATR_ASSESSMENT_COMMENT));
				assessment.setPublicationDate(Date.valueOf(LocalDate.now()));
				assessment.setPublicationTime(Time.valueOf(LocalTime.now()));
				assessment.setEditDate(Date.valueOf(LocalDate.now()));
				assessment.setEditTime(Time.valueOf(LocalTime.now()));
				assessment.setVideogameId(request.getParameter(Assessment.ATR_ASSESSMENT_VIDEOGAMEID));
				assessment.setUserId(request.getParameter(Assessment.ATR_ASSESSMENT_USERID));
	
				(new AssessmentDAO()).create(assessment);
			}
		} catch(Exception e) {
			return "/mod/error.jsp?ERROR_TYPE=" + ErrorType.CREATE_ASSESSMENT_ERROR;
		}
		
		
		return "/mod/videogame.jsp?" + Videogame.ATR_VIDEOGAME_ID + "=" + assessment.getVideogameId();

	}
}
