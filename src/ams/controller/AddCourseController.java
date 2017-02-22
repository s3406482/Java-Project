package ams.controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ams.model.CoreCourse;
import ams.model.Course;
import ams.model.ElectiveCourse;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSModel;


import ams.test.Tester;
import ams.view.Toolbar;
import ams.view.grid.AbstractCourseCell;
import ams.view.grid.CoreCourseCell;
import ams.view.grid.ElectiveCourseCell;
import ams.view.grid.GridCell;

public class AddCourseController implements ActionListener
{

	private Toolbar toolbar;
	private AMSModel model;
	
	private LimitedJTextField codeEntry = new LimitedJTextField(8);
	private JTextField nameEntry = new JTextField(20);
	
	public AddCourseController(Toolbar toolbar)
	{
		this.toolbar = toolbar;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		model = this.toolbar.getAMSMainView().getModel();
		
		

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,8,10,20));
		panel.setVisible(true);
		
		String[] cse = {"Core", "Elective"};
		JComboBox course = new JComboBox(cse);
		
		

	    
	    String[] cp = {"12", "6"};
	    JComboBox credits = new JComboBox(cp);
	    JCheckBox[] boxes = null;
	    //PreReq check boxes that only display current courses
	    JLabel preReqs = new JLabel("");
	    
	   	Course [] courses = model.getAllCourses();
	   	if(model.getAllCourses() != null)
	   	{
	   		String [] codes = new String[courses.length];
		
	   		boxes = new JCheckBox[courses.length];
		
	   		for(int i = 0 ; i<courses.length; i++)
	   		{
	   			codes[i] = courses[i].getCode();
	   			boxes[i] = new JCheckBox(codes[i]);
	   			preReqs = new JLabel("Enter Prerequisites");
	   			
	   		}
	   	}
	   
	 
	   	codeEntry = new LimitedJTextField(8);
 
		Object inputs[] = {course, new JLabel("Enter Course Code")
				,codeEntry,new JLabel("Enter Course Name"), nameEntry,
				new JLabel("Enter Credit Points"),credits, preReqs, boxes};

		    JOptionPane optionPane = new JOptionPane();
		    optionPane.setMessage(inputs);
		    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		    JDialog dialog = optionPane.createDialog(panel, "Enter Course Information");
		    dialog.setVisible(true);
		


		    String courseName = nameEntry.getText();
		    String courseCode = codeEntry.getText();
		    String courseType = (String) course.getSelectedItem();
		    String creditPoints = (String) credits.getSelectedItem();
		    String[] preRequisites = null;
		    if(boxes != null)
		    {
		    	//counters for prereq checks
		    	int x = 0;
		    	int a = 0;
		    	for(int i =0; i< boxes.length; i++)
		    	{
		    		if(boxes[i].isSelected())
		    		{
		    			a++;
		    		}
		    	}
		    	if(a != 0)
		    	{
		    		preRequisites = new String[a];
		    	
		    		for(int i =0; i< boxes.length; i++)
		    		{
		    		
		    			if(boxes[i].isSelected())
		    			{
		    				preRequisites[x] = boxes[i].getText();
		    				x++;
		    				
		    			}
		    		
		    		}
		    	}
		   
		    }	
		    
	    	
		    //data entry checks 
			if(courseCode.matches("[A-Za-z0-9]+"))
				{
					if(courseCode.length() == 8)
					{
						if(courseName.length() > 3 )
						{	
							// adding of new courses depending on option selected
							if(courseType == "Core")
						    {
						    	try
								{
						    		if(preRequisites == null)
						    		{
						    			model.addCourse(new CoreCourse(courseCode, 
												courseName, null));
					
						    		}
						    		else
						    		{
						    			model.addCourse(new CoreCourse(courseCode, 
						    					courseName, preRequisites));
					
						    		}
								}
								catch (ProgramException e)
								{
									
								}

						    }else if(courseType == "Elective")
						    {
						    	try
								{	
						    		int points = Integer.parseInt(creditPoints);
						    		if(preRequisites == null)
						    		{
						    			model.addCourse(new ElectiveCourse(courseCode,
												courseName, points, null));
					
						    		}
						    		else
						    		{
						    			model.addCourse(new ElectiveCourse(courseCode,
												courseName, points, preRequisites));
		    			
						    		}
								}
						    	catch (ProgramException e)
								{
								
								}
						    }
							
						}
						else
						{
							
							JOptionPane.showMessageDialog(null, 
								"Warning Course Name must be more than 3 characters");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, 
								"Warning Course Code must be 8 characters in length");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,
					"Warning Course Code must only contain alphanumerics");
				
				}
			//reset the text fields
			nameEntry.setText(null);
			codeEntry.setText(null);
//			
//			FOR TESTING PURPOSES ONLY!!!
//		    Tester tester = new Tester();			
//		    tester.populateModel(model);		
//			

			
			//null check to avoid null pointer if no course was added
			if(model.getAllCourses() != null)
			{
				courses = model.getAllCourses();
		    
				GridCell [] cells = new AbstractCourseCell[courses.length];
		    
				for(int i = 0; i<courses.length; i++)
				{
				
					if(courses[i] instanceof CoreCourse)
					{
						cells[i] = new CoreCourseCell(courses[i].toString());
    		    	}
    		    	else
    		    	{
    		    		cells[i] = new ElectiveCourseCell(courses[i].toString());
    		    	}
				}
		    
				toolbar.getAMSMainView().updateGrid(cells);
				toolbar.getAMSMainView().updateStatusBar(model.getProgram().toString());
			}
	}

}
