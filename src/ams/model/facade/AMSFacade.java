package ams.model.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ams.model.*;
import ams.model.exception.*;
import ams.model.visitor.CourseCount;


/**
 * @author Mikhail Perepletchikov
 */
public class AMSFacade implements AMSModel {
	
	private University uni;
	
	public AMSFacade()
	{
		uni = new University();
		
	}

	@Override
	public void addStudent(Student newStudent)
	{
		uni.addStudent(newStudent);
		
	}

	@Override
	public Student getStudent()
	{
		
		return uni.getStudent();
	}

	@Override
	public void addProgram(Program program)
	{
		uni.addProgram(program);
		
	}

	@Override
	public Program getProgram()
	{
		
		return uni.getProgram();
	}

	@Override
	public void addCourse(Course course) throws ProgramException
	{
		uni.addCourse(course);
	}

	@Override
	public void removeCourse(String courseId) throws ProgramException
	{
		uni.removeCourse(courseId);
		
	}

	@Override
	public Course getCourse(String courseCode)
	{
		
		return uni.getCourse(courseCode);
	}

	@Override
	public Course[] getAllCourses()
	{	
		
		Map<String, Course> course = uni.getAllCourses();
		if (course.isEmpty())
		{
			return null;
		}
		else
		{
		return course.values().toArray(new Course[course.size()]);
		}
		
	}

	@Override
	public void enrollIntoCourse(String courseID) throws EnrollmentException
	{
		uni.enrollIntoCourse(courseID);
		
	}

	@Override
	public void withdrawFromCourse(String courseID) throws EnrollmentException
	{
		uni.withdrawFromCourse(courseID);
		
	}

	@Override
	public boolean addResult(Result result)
	{
		return uni.addResult(result);
	}

	@Override
	public Result[] getResults()
	{
		List<Result> re = uni.getResults();
		System.out.println(re);
		if(re.isEmpty())
		{
			return null;
		}
		else
		{
			return re.toArray(new Result[re.size()]);
		}
	}

	@Override
	public Course[] getCurrentEnrollment()
	{
		

		List <Course> cse = uni.getCurrentEnrollment();
		

		if(cse.isEmpty())
		{
			return null;
		}
		else
		{
			
			return cse.toArray(new Course[cse.size()]);
			
		}
	}

	@Override
	public int calculateCurrentLoad()
	{
		
		return uni.calculateCurrentLoad();
	}

	@Override
	public int calculateCareerPoints()
	{
		
		return uni.calculateCareerPoints();
	}

	@Override
	public int countCoreCourses()
	{
		
		Course[] courses = getAllCourses();
		CourseCount visitor = new CourseCount();
		for (int i = 0; i<courses.length; i++)
			courses[i].accept(visitor);
		return visitor.getCoreCount();
	}

	@Override
	public int countElectiveCourses()
	{
		Course[] courses = getAllCourses();
		CourseCount visitor = new CourseCount();
		for (int i = 0; i<courses.length; i++)
			courses[i].accept(visitor);
		return visitor.getElectiveCount();
	}


    /* you need to implement all AMSModel methods here */
   
}