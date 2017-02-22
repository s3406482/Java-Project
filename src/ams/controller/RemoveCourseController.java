package ams.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ams.model.CoreCourse;
import ams.model.Course;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSModel;
import ams.view.Toolbar;
import ams.view.grid.AbstractCourseCell;
import ams.view.grid.CoreCourseCell;
import ams.view.grid.ElectiveCourseCell;
import ams.view.grid.GridCell;

public class RemoveCourseController implements ActionListener
{

	private Toolbar toolbar;
	private AMSModel model;
	
	public RemoveCourseController(Toolbar toolbar)
	{
		this.toolbar = toolbar;
	}

	
	
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		model = this.toolbar.getAMSMainView().getModel();
		
		if(model.getAllCourses() != null)
		{
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,4));
		panel.setVisible(true);

	
		Course [] courses = model.getAllCourses();
		String [] codes = new String[courses.length];
		
		JCheckBox[] boxes = new JCheckBox[courses.length];
		
		for(int i = 0 ; i<courses.length; i++)
		{
			codes[i] = courses[i].getCode();
			boxes[i] = new JCheckBox(codes[i]);
			panel.add(boxes[i]);
		}
		
		
		
		int programInfo = JOptionPane.showConfirmDialog(null, panel, 
				"Remove which courses?", JOptionPane.OK_CANCEL_OPTION);
		
		
		GridCell [] cells = new AbstractCourseCell[courses.length];
		
		
		for(int i = 0; i < boxes.length; i++)
		{
			if(boxes[i].isSelected())
			{
				try
				{
					
					model.removeCourse(boxes[i].getText());
					
					if(model.getAllCourses() != null)
					{
						courses = model.getAllCourses();
				    
						cells = new AbstractCourseCell[courses.length];
				    
						for(int x = 0; x<courses.length; x++)
						{
							if(courses[x] instanceof CoreCourse)
							{
								cells[x] = new CoreCourseCell(courses[x].toString());
		    		    	}
		    		    	else
		    		    	{
		    		    		cells[x] = new ElectiveCourseCell(courses[x].toString());
		    		    	}
						}
				    
						
						
					}
					toolbar.getAMSMainView().updateGrid(cells);
					toolbar.getAMSMainView().updateStatusBar(model.getProgram().toString());
					
				}
				catch (ProgramException e)
				{
					
					JOptionPane.showMessageDialog(null, 
							"Warning cannot remove a prerequisite course!!");
				}
			}
		}
		

		

		}
	}

}
