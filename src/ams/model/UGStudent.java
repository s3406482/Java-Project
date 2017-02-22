package ams.model;

import ams.model.exception.EnrollmentException;

public class UGStudent extends AbstractStudent
{
	// total load amount allowed for UG Student
	public static final int maxLoad = 60;
	
	public UGStudent(int  StudentID, String FullName)
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
		
		int totalload = 0;
		for(int i = 0; i<currentEnrollment.size(); i++)
		{
			int load = currentEnrollment.get(i).getCreditPoints();
			totalload = load + totalload;
		}
		
		if(totalload > maxLoad)
		{
			throw new EnrollmentException();
		}
		
		
// 		this will check for pre requisites then match them to all listed course
//		codes. Used a tagger to make sure that multiple prerequisites would be 
//		caught.
		
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
			if(prereqcheck != course.getPreReqs().length)
			{
				
				throw new EnrollmentException();
			}
		}
		
	}
}