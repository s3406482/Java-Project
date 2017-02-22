package ams.model;

import ams.model.exception.EnrollmentException;

public interface Enrollable
{
	public abstract void enrollIntoCourse(Course course) throws EnrollmentException;
	
	public abstract void withdrawFromCourse(Course course) throws EnrollmentException;
		
}
