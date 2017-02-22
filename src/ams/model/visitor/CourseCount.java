package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;

public class CourseCount implements Visitor
{
	private int coreCount = 0;
	private int electiveCount = 0;
	
	@Override
	public void visit(ElectiveCourse course)
	{
		electiveCount++;
	}
	
	@Override
	public void visit(CoreCourse course)
	{
		coreCount++;
	}

	public int getCoreCount()
	{
		return coreCount;
	}

	public int getElectiveCount()
	{
		return electiveCount;
	}

}
