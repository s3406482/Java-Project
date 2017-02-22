package ams.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;
//import ams.model.facade.Result;

public class University
{
	private Student student;
	private Program program;
	
	
	
	public Program getProgram()
	{
		return program;
	}

	
	public Student getStudent()
	{
		return student;
	}
	
	
	public void addStudent(Student newStudent)
	{
		this.student = newStudent;
	}

	public void addProgram(Program program)
	{
		this.program = program;
	}
	
	public void addCourse(Course course) throws ProgramException
	{
		program.addCourse(course);
	}
	
	public Course getCourse(String courseCode)
	{
		return program.getCourse(courseCode);
	}
	

	public Map<String, Course> getAllCourses()
	{
		return program.getAllCourses();
	}


	public int calculateCurrentLoad()
	{
		
		return student.calculateCurrentLoad();
	}


	public void removeCourse(String courseId) throws ProgramException
	{
		program.removeCourse(courseId);
		
	}


	public void enrollIntoCourse(String courseID) throws EnrollmentException
	{
		Course course = getCourse(courseID);
		student.enrollIntoCourse(course);
		
	
	}


	public List<Course> getCurrentEnrollment()
	{
		
		return student.getCurrentEnrollment();
	}


	public void withdrawFromCourse(String courseID) throws EnrollmentException
	{
		Course course = getCourse(courseID);
		student.withdrawFromCourse(course);
		
	}


	public boolean addResult(Result result)
	{
		
		return student.addResult(result);
	}


	public List<Result> getResults()
	{
		
		return student.getResults();
	}


	public int calculateCareerPoints()
	{
		
		return student.calculateCareerPoints();
	}



}	
