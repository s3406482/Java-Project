package ams.model;

public class Result
{
	
	private Course course;
	private boolean grade;
	
	public Result(Course course, boolean grade)
	{
		this.course = course;
		this.grade = grade;
	}
	
	public boolean equals (Object in)
	{
		Result obj = (Result) in ;
		return course.equals(obj.getCourse());
	
	}
	
	
	public Course getCourse()
	{
		
		return course;
	}
	
	
	public boolean getGrade()
	{
		return grade;
	}
	
	public String toString()
	{
		if(grade == false)
		{
			return course.getCode() + ":FAIL";
		}
		else
		{
			return course.getCode() + ":PASS";
		}
				
	}
//	public void add(Result result)
//	{
//		
//		
//	}
}
