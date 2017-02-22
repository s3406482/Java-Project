package ams.controller;


import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import ams.model.CoreCourse;
import ams.model.Course;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSModel;
import ams.view.CourseGrid;
import ams.view.grid.AbstractCourseCell;
import ams.view.grid.CoreCourseCell;
import ams.view.grid.ElectiveCourseCell;
import ams.view.grid.GridCell;

public class CellController implements MouseListener
{

	private CourseGrid courseGrid;
	private AMSModel model;
	private AbstractCourseCell cell;

	public CellController(CourseGrid courseGrid)
	{
		this.courseGrid = courseGrid;
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		model = courseGrid.getAMSMainView().getModel();
		cell = (AbstractCourseCell) e.getComponent();
		
		Course [] courses = model.getAllCourses();
		GridCell [] cells = new AbstractCourseCell[courses.length];
		
		int remove = JOptionPane.showConfirmDialog(null, 
				"You sure you want to remove this course?", null,
				JOptionPane.YES_NO_OPTION);
		
		int a = courses.length;
		
		if(remove == JOptionPane.OK_OPTION)
		{

				if(model.getAllCourses() != null)
				{
					for(int i = 0; i < a; i++)
					{
						if(courses[i].getCode().equals(cell.getCode()))
						{
							try
							{
							model.removeCourse(courses[i].getCode());
							courses = model.getAllCourses();
							courseGrid.remove(cell);
							
							if(courses != null)
							{
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
							}}
							catch (ProgramException d)
							{
								JOptionPane.showMessageDialog(null, 
									"Warning cannot remove a prerequisite course!!");
							}
						}
				}
					courseGrid.getAMSMainView().updateGrid(cells);
					courseGrid.getAMSMainView().updateStatusBar(model.getProgram().toString());
			}
				
			}

		}


	@Override
	public void mouseEntered(MouseEvent e)
	{
	

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
