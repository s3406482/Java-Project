package ams.model;

import ams.model.visitor.Visitor;



public class ElectiveCourse extends AbstractCourse
{
	
	
	public ElectiveCourse(String courseCode, String courseTitle, int creditPoints,
			String[] prerequisites)
	{
		super(courseCode, courseTitle, prerequisites);
		this.creditPoints = creditPoints;
	}

	@Override
	public String toString()
	{
		return super.toString() + ":ELECTIVE";
		
		
	}
	public void accept(Visitor visitor)
	{
		visitor.visit(this);;
	}

}
	
