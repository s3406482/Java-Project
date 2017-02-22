package ams.model;



public abstract class AbstractCourse implements Course
{

	protected String courseCode;
	protected String courseTitle;
	protected int creditPoints;
	protected String[] prerequisite;
	protected String type;
	
	public AbstractCourse(String courseCode, String courseTitle,
			String[] prerequisite)
	{
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.creditPoints = creditPoints;
		this.prerequisite = prerequisite;
		
	}
	
	
	public String getCode()
	{
		return courseCode;
	}
	
	
	public String getTitle()
	{
		return courseTitle;
	}
	

	public int getCreditPoints()
	{
		return creditPoints;
	}
	
	public String[] getPreReqs()
	{

		return prerequisite;
	}

	
	
	public String toString()
	{
		if(prerequisite == null)
		{
			return courseCode + ":" + courseTitle + ":" + 
						getCreditPoints();
		}
		else
		{
			
		for (int i = 0; i < prerequisite.length; i++) {
			   return courseCode + ":" + courseTitle + ":" + 
					   getCreditPoints() + ":" + (prerequisite[i]);
			}
		
		}
		return null;		
	}

}
