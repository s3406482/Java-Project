package ams.model;

import java.util.List;

public interface Student extends Enrollable
{

	public abstract List<Result> getResults();

	public abstract String getFullName();

	public abstract int getStudentID();

	public abstract int calculateCurrentLoad();

	public abstract List<Course> getCurrentEnrollment();

	public abstract boolean addResult(Result result);

	public abstract int calculateCareerPoints();

	

}