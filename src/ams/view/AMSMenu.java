package ams.view;

import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import ams.controller.MenuBarController;



@SuppressWarnings("serial")
public class AMSMenu extends JMenuBar
{	
	private AMSMainView amsMainView;



	private ActionListener menuBarController;
	private JMenuItem reset;
	private JMenuItem exit;
	private JMenuItem addProgram;
	private JMenuItem addCourse;
	private JMenuItem removeCourse;
	
	public AMSMenu(AMSMainView amsMainView)
	{
		super();
		this.amsMainView = amsMainView;
		
		JMenu file = new JMenu("Options");
		this.add(file);
	
		
		addProgram = new JMenuItem("Add Program");
		file.add(addProgram);
		this.menuBarController = new MenuBarController(this);
		addProgram.addActionListener(menuBarController);
		
		
		addCourse = new JMenuItem("Add Course");
		file.add(addCourse);
		addCourse.addActionListener(menuBarController);
		addCourse.setEnabled(false);
		
		removeCourse = new JMenuItem("Remove Course");
		file.add(removeCourse);
		removeCourse.addActionListener(menuBarController);
		removeCourse.setEnabled(false);
		
		
		reset = new JMenuItem("Reset");
		file.add(reset);
		reset.addActionListener(menuBarController);
		reset.setEnabled(false);
		
		exit = new JMenuItem("Exit");
		file.add(exit);
		exit.addActionListener(menuBarController);
		
	}
	
	public AMSMainView getAMSMainView()
	{
		
		return amsMainView;
	}

	public JComponent getReset()
	{
		
		return reset;
	}

	public JComponent getRemoveCourse()
	{
		
		return removeCourse;
	}

	public JComponent getAddCourse()
	{
		
		return addCourse;
	}
	
	
}
