package ams.model;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;

import ams.model.exception.ProgramException;
import ams.model.visitor.CourseCount;

public class Program
{
	private String programCode;
	private String programTitle;
	private Course course;
	// collection of courses in a hashmap
	private Map <String, Course> courses = new HashMap <String, Course>(); 
	
	
	
	public Program(String programCode, String programName)
	{
		this.programCode = programCode;
		this.programTitle = programName;
		
	}

	public String getCode()
	{
		return programCode;
	}

	public String getProgramName()
	{
		return programTitle;
	}


	public void addCourse(Course course) throws ProgramException
	{	
		if(course.getPreReqs() != null)
		{
			int i = 0;
			// iterate through the list to check prerequisite exists if
			// not throw correct exception
			Iterator it = courses.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry c = (Map.Entry)it.next();
				
				//had to implement a tagging/count system so would not throw 
				//concurrent modification exception
				for(int x = 0; x<course.getPreReqs().length; x++)
				{
					if(c.getKey().equals(course.getPreReqs()[x]))
					{
						i++;
					}
				}
			}
				
			
			if(i < course.getPreReqs().length)
			{	
				
				throw new ProgramException();
			}
			
		}
		courses.put(course.getCode(),course);
		
	
	}

	public Course getCourse(String courseCode)
	{
		return courses.get(courseCode);
     	
		

		
	}
	
	public String toString()
	{
		return this.programCode + ":" + this.programTitle;
				
	}
	
	public Map<String, Course> getAllCourses()
	{
	
		return courses;
	}

	public void removeCourse(String courseId) throws ProgramException
	{

		Iterator it = courses.entrySet().iterator();
		
//		check to ensure if a course is a pre requisite it is not removed first
		while (it.hasNext())
		{
			Map.Entry c = (Map.Entry)it.next();
			if(c.getKey() != courseId)
			{
				String x = c.getValue().toString();
				if(x.contains(courseId))
				{
					throw new ProgramException();
				}
			}
		}
		
		courses.remove(courseId);	
		
			
	}


	
}
