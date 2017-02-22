package ams.view;


import java.awt.BorderLayout;


import javax.swing.JFrame;

import ams.controller.WindowController;
import ams.model.facade.AMSModel;
import ams.view.grid.GridCell;

@SuppressWarnings("serial")
public class AMSMainView extends JFrame
{
	private AMSModel model;
	private SubView toolbar;
	private AMSMenu menuBar;
	private SubView statusBar;
	private SubView courseGrid;
	private WindowController windowController;
	
	public AMSMainView(AMSModel model)
	{
		super("Academic Management System");
		setSize(800,600);
		this.model = model;
		
		
		menuBar = new AMSMenu(this);
		this.setJMenuBar(menuBar);
		
		setLayout (new BorderLayout());
		
		toolbar = new Toolbar(this);
		this.add(toolbar, BorderLayout.NORTH);
		statusBar = new StatusBar(this);
		this.add(statusBar, BorderLayout.SOUTH);
		courseGrid = new CourseGrid(this);
		this.add(courseGrid, BorderLayout.CENTER);
	
		
		
		//window controller for top right x button
		this.windowController = new WindowController(this);
		this.addWindowListener(windowController);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	

	public AMSModel getModel()
	{
		return model;
	}
	
	public void updateStatusBar(String programInfo)
	{
		
		((StatusBar)statusBar).update(programInfo);
		statusBar.revalidate();
	}
	

	public void updateGrid(GridCell[] cells)
	{
		
		this.remove(courseGrid);
		
		courseGrid = new CourseGrid(this);
		
		this.add(courseGrid, BorderLayout.CENTER);
		((CourseGrid) courseGrid).updateGrid(cells);
		courseGrid.revalidate();
	}


	public Toolbar getToolbar()
	{
		
		return (Toolbar) toolbar;
	}
	
	public AMSMenu getAmsMenu()
	{
		return menuBar;
	}
	

}
