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
import ams.model.Program;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSModel;
import ams.view.AMSMenu;
import ams.view.grid.AbstractCourseCell;
import ams.view.grid.CoreCourseCell;
import ams.view.grid.ElectiveCourseCell;
import ams.view.grid.GridCell;

public class MenuBarController implements ActionListener
{

	private AMSMenu amsMenu;
	private AMSModel model;
	

	public MenuBarController(AMSMenu amsMenu)
	{
		this.amsMenu = amsMenu;
	}

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e)
	{
		model = this.amsMenu.getAMSMainView().getModel();
		
		
		if(e.getActionCommand() == "Add Program")
		{
			JTextField nameEntry = new JTextField(20);
			LimitedJTextField codeEntry = new LimitedJTextField(6);
			JPanel panel;
			panel = new JPanel();
			panel.setLayout(new GridLayout(2,2,5,5));
			panel.add(new JLabel("Enter Program Code"));
		    
			panel.add(codeEntry);
		    panel.add(new JLabel("Enter Program Name"));
		    panel.add(nameEntry);
			
			int programInfo = JOptionPane.showConfirmDialog(null, panel, "Enter program info",
					JOptionPane.OK_CANCEL_OPTION);
			
			//if/else loops to check data entry
			if(programInfo == JOptionPane.OK_OPTION)
			{
				String programCode = codeEntry.getText();
				String programName = nameEntry.getText();
				if(programCode.matches("[A-Za-z0-9]+"))
				{
					if(programCode.length() == 6)
					{
						if(programName.length() > 3 )
						{
							model.addProgram(new Program(programCode, programName));
						
							amsMenu.getAMSMainView().updateStatusBar(model.getProgram().toString());
						}
						else
						{
							JOptionPane.showMessageDialog(null, 
								"Warning Program Name must be more than 3 characters");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, 
								"Warning Program Code must be 6 characters in length");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Warning Program Code must only contain alphanumerics");
				}
//				 to enable buttons now that program is initialised
				amsMenu.getAddCourse().setEnabled(true);
				amsMenu.getRemoveCourse().setEnabled(true);
				amsMenu.getReset().setEnabled(true);
				amsMenu.getAMSMainView().getToolbar().getRemoveCourse().setEnabled(true);
				amsMenu.getAMSMainView().getToolbar().getAddCourse().setEnabled(true);
				amsMenu.getAMSMainView().getToolbar().getReset().setEnabled(true);
				amsMenu.getAMSMainView().updateStatusBar(model.getProgram().toString());	
				amsMenu.getAMSMainView().updateGrid(null);
			}
			
			
			

		
			
		}
		else if(e.getActionCommand() == "Add Course")
		{
			JTextField nameEntry = new JTextField(20);
			LimitedJTextField codeEntry = new LimitedJTextField(8);
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
			    	
			    	
			    	preRequisites = new String[a];
			    	for(int i =0; i< boxes.length; i++)
			    	{
			    		
			    		if(boxes[i].isSelected())
			    		{
			    			preRequisites[x] = boxes[i].getText();
			    			x++;
			    			System.out.println(x);
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
									catch (ProgramException d)
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
							    	catch (ProgramException d)
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
				
					amsMenu.getAMSMainView().updateGrid(cells);
					amsMenu.getAMSMainView().updateStatusBar(model.getProgram().toString());
				}
		}
		else if(e.getActionCommand() == "Remove Course")
		{
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0,4));
			panel.setVisible(true);
//			JCheckBox course = new JCheckBox("None");
			
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
					}
					catch (ProgramException d)
					{
						
						JOptionPane.showMessageDialog(null, 
								"Warning cannot remove a prerequisite course!!");
					}
				}
			}
			amsMenu.getAMSMainView().updateGrid(cells);
			amsMenu.getAMSMainView().updateStatusBar(model.getProgram().toString());
			
				
		}
		else if(e.getActionCommand() == "Reset")
		{
			int resetOption = JOptionPane.showConfirmDialog(null,
							"You sure you want to reset?!", null, JOptionPane.YES_NO_OPTION);
			
			if(resetOption == JOptionPane.OK_OPTION)
			{
				
				model.addProgram(new Program(null,null));
				amsMenu.getAMSMainView().updateStatusBar("Please add a program");
				amsMenu.getAMSMainView().updateGrid(null);
				amsMenu.getAddCourse().setEnabled(false);
				amsMenu.getRemoveCourse().setEnabled(false);
				amsMenu.getReset().setEnabled(false);
			}
		}	
			else
			{
				
				int exitOption = JOptionPane.showConfirmDialog(null, 
						"You sure you want to exit??", null, JOptionPane.YES_NO_OPTION);
				
				
				
				if(exitOption == JOptionPane.OK_OPTION)
				{
					
					amsMenu.getAMSMainView().dispose();
					
				}
			}
		
		
		
	}

	}
