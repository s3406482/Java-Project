package ams.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ams.model.CoreCourse;
import ams.model.Course;
import ams.model.ElectiveCourse;
import ams.model.Program;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSFacade;
import ams.model.facade.AMSModel;

public class Tester
{

	private static final String P1 = "COSC1073";
	private static final String P2 = "COSC2136";
	private static final String MATHS = "MATH1074";
	private static final String MAD = "COSC2309";
	private static final String DISTRIBUTED = "COSC1197";


	private  AMSModel model;

	private static Course coreCourse1, coreCourse2, coreCourse3;
	private static Course electiveCourse1, electiveCourse2; 
	
//	public void populateModel(AMSModel model) {
//		model = new AMSFacade();
//		initialiseCourses();
//		addCourses();
//	}

	

	public static void initialiseCourses() {
		// create sample courses
		coreCourse1 = new CoreCourse(P1, "Programming 1", null);
		coreCourse2 = new CoreCourse(MATHS, "Mathematics for Computing", null);
		coreCourse3 = new CoreCourse(P2, "Programming 2", new String[] { P1 });
	

		electiveCourse1 = new ElectiveCourse(MAD,
				"Mobile Application Development", 6, new String[] { P2 });
		electiveCourse2 = new ElectiveCourse(DISTRIBUTED,
				"Distributed Systems", 12, new String[] { MAD });
	
	}

	private void addCourses()
	{
		try{
			model.addCourse(coreCourse1);
			model.addCourse(coreCourse2);
			model.addCourse(coreCourse3);
			model.addCourse(electiveCourse1);
			model.addCourse(electiveCourse2);
		}
		catch(ProgramException e){
			
		}
	}



	public void populateModel(AMSModel model)
	{
		this.model = model;
		model = new AMSFacade();
		initialiseCourses();
		addCourses();
		
	}


	
}
