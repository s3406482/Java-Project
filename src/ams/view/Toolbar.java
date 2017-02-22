package ams.view;

import java.awt.Color;

import javax.swing.JButton;


import ams.controller.AddCourseController;
import ams.controller.AddProgramController;
import ams.controller.RemoveCourseController;
import ams.controller.ResetController;


@SuppressWarnings("serial")
public class Toolbar extends SubView
{
	private JButton addProgram;
	private JButton addCourse;
	private JButton removeCourse;
	private JButton reset;
	private AddProgramController programController;
	private ResetController resetController;
	private AddCourseController courseController;
	private RemoveCourseController removeController;
	
	
	public Toolbar(AMSMainView amsMainView)
	{
		super(amsMainView);
		this.setBackground(Color.black);
		
		addProgram = new JButton("Add New Program");
		this.add(addProgram);
		this.programController = new AddProgramController(this);
		addProgram.addActionListener(programController);
		
		
		addCourse = new JButton("Add Course");
		this.add(addCourse);
		this.courseController = new AddCourseController(this);
		addCourse.addActionListener(courseController);
		addCourse.setEnabled(false);
		
		
		removeCourse = new JButton("Remove Course");
		this.add(removeCourse);
		this.removeController = new RemoveCourseController(this);
		removeCourse.addActionListener(removeController);
		removeCourse.setEnabled(false);
		
		reset = new JButton("Reset");
		this.add(reset);
		this.resetController = new ResetController(this);
		reset.addActionListener(resetController);
		reset.setEnabled(false);
		
	}
	
	
	public JButton getAddProgram()
	{
		return addProgram;
	}

	public JButton getAddCourse()
	{
		return addCourse;
	}
	
	public JButton getRemoveCourse()
	{
		return removeCourse;
	}
	
	public JButton getReset()
	{
		return reset;
	}
	
	
}
