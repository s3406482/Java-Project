package ams.model;

import ams.model.visitor.Visitor;

public class CoreCourse extends AbstractCourse 
{
	
	
	// final int for credit points as is always 12 for core courses
	public static final int corecreditPoints = 12;
	
	public CoreCourse(String courseCode, String courseTitle,String[] prerequisites)
	{
		
		super(courseCode, courseTitle, prerequisites);
		creditPoints = corecreditPoints;

		
	}
	
	@Override
	public String toString()
	{
		
		return super.toString() + ":CORE";
		
		
	}
	@Override
	public int getCreditPoints()
	{
		return corecreditPoints;
	}
	
	public void accept(Visitor visitor)
	{
		visitor.visit(this);;
	}

	
}
