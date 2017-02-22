package ams.model;

import java.util.ArrayList;
import java.util.List;

import ams.model.exception.EnrollmentException;

public abstract class AbstractStudent implements Student
{
	
	private String FullName;
	private int StudentID;
	protected int maximumload;
	
	protected List <Result> results = new ArrayList<Result>();
	protected List <Course> currentEnrollment = new ArrayList<Course>();
	
	public AbstractStudent(int StudentID, String FullName,int maximumload)
	{
		this.FullName = FullName;
		this.StudentID = StudentID;
		this.maximumload = maximumload;
		
	}
	
	
	


	public String getFullName()
	{
		return FullName;
	}
	
	
	public int getStudentID()
	{
		return StudentID;
	}
	
	
	public int calculateCurrentLoad()
	{
		int current = 0;
		for(int i = 0; i<currentEnrollment.size(); i++)
		{
			int currentcoursepoints = currentEnrollment.get(i).getCreditPoints();
			current = current + currentcoursepoints;
		}
		
		return current;
	}
	
	public int calculateCareerPoints()
	{
		int careerpoints = 0;
		
//		loop to add all the careerpoints together then return them
		for(int i = 0; i<results.size(); i++)
		{
			if(results.get(i).getGrade() == true)
			{
			int currentpoints = results.get(i).getCourse().getCreditPoints();
			careerpoints = currentpoints + careerpoints;
			}
		}
		return careerpoints;
	}
	
	public void enrollIntoCourse(Course course) throws EnrollmentException
	{

		
	}
	
	public void withdrawFromCourse(Course course) throws EnrollmentException
	{
		currentEnrollment.remove(course);
	}
	
	public List<Course> getCurrentEnrollment()
	{
		
		return currentEnrollment;
	}
	
	public String toString() 
	{
	    return this.StudentID + ":" + this.FullName + ":" + maximumload;
	}
	
	public boolean addResult(Result result)
	{

//		check to ensure that a fail will be overridden by a pass mark
		if(results.contains(result))
		{
			results.remove(result);
		}
		
		results.add(result);
		
		currentEnrollment.remove(result.getCourse());
		
		return false;
		
		
	}
	public List<Result> getResults()
	{
		
		return results;
	}
}
