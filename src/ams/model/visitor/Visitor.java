package ams.model.visitor;

import ams.model.CoreCourse;
import ams.model.ElectiveCourse;

public interface Visitor
{
	public void visit(ElectiveCourse course);
	public void visit(CoreCourse course);
}
