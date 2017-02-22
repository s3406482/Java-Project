package ams.view;

import java.awt.Color;

import javax.swing.JLabel;
import ams.model.facade.AMSModel;

@SuppressWarnings("serial")
public class StatusBar extends SubView
{
	private JLabel programName;
	private AMSModel model;
	
	public StatusBar(AMSMainView amsMainView)
	{
		super(amsMainView);
		this.setBackground(Color.black);

		
		programName = new JLabel("Please add a program");
		this.add(programName);
		programName.setForeground(Color.white);
		
		model = amsMainView.getModel();

	}

	public void update(String programInfo)
	{
		int core = 0;
		int elective = 0;
		
		if(model.getAllCourses() != null)
		{
		core = model.countCoreCourses();
		elective = model.countElectiveCourses();
		programName.setText(programInfo + "            Core Courses: " + core + 
				"   Elective Courses: " + elective);
		
		}
		else
		{
			programName.setText(programInfo);
			core = 0;
			elective = 0;
		}
	
		
	}
	

}
