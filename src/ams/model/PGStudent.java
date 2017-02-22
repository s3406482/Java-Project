package ams.model;

import ams.model.exception.EnrollmentException;

public class PGStudent extends AbstractStudent
{
	// total load amount allowed for PG Student
	public static final int maxLoad = 48;
	
	public PGStudent(int StudentID, String FullName)
	{
		super(StudentID, FullName, maxLoad);
	}
	
	@Override
	public void enrollIntoCourse(Course course) throws EnrollmentException
	{
//		makes sure that the student cannot enrol into the same course twice
//		and also cannot re enrol if passed mark was acheived
		if(currentEnrollment.contains(course))
		{
			throw new EnrollmentException();
		}
		else if(results.contains(course))
		{
			for(int i = 0; i<results.size(); i++)
			{
				if(results.get(i).getGrade() == true)
				{
					throw new EnrollmentException();
				}
			}
		}

		currentEnrollment.add(course);
		
//		this will make sure that the students maximum load is not exceeded
//		extra rule
		
		int totalload = 0;
		for(int i = 0; i<currentEnrollment.size(); i++)
		{
			int load = currentEnrollment.get(i).getCreditPoints();
			totalload = load + totalload;
		}
		
		if(totalload > maxLoad)
		{
			if(totalload <= 54)
			{
				for(int i = 0; i<results.size(); i++)
				{
					if(results.get(i).getGrade() == false)
					{
						throw new EnrollmentException();
					}
				}
			}
			else throw new EnrollmentException();
		}
		
		
// 		this will check for pre requisites then match them to all listed course
//		codes. As post graduate student needs only 1 pre requisite don't have to
//		for multiple
		
		if(course.getPreReqs() != null)
		{
			int prereqcheck = 0;
			for(int i = 0; i < results.size(); i++)
			{
				for(int x = 0; x<course.getPreReqs().length; x++)
				{
					if(results.get(i).getCourse().getCode().equals(course.getPreReqs()[x]))
					{
						if(results.get(i).getGrade() == true)
						{
							prereqcheck++;
						}
					}
				}
			}
			if(prereqcheck == 0)
			{
				
				throw new EnrollmentException();
			}
		}
		
	}
		
		
	

}
