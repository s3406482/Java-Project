package ams.view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


import ams.controller.CellController;
import ams.view.grid.GridCell;

@SuppressWarnings("serial")
public class CourseGrid extends SubView
{
	
	
	private MouseListener cellController;

	public CourseGrid(AMSMainView amsMainView)
	{
		super(amsMainView);

		this.setBackground(Color.white);
		
	}
	
	public void updateGrid(GridCell[] cells)
	{
		//this is so the course grid always fills screen
		if(cells != null)
		{
			if(cells.length == 1)
			{
				setLayout(new GridLayout(1,1));
			}
			if(cells.length == 2)
			{
				setLayout(new GridLayout(0,2));
			}
			if(cells.length == 3)
			{
				setLayout(new GridLayout(0,3));
			}
			if(cells.length > 3)
			{
				setLayout(new GridLayout(0,4));
			}
		
			for(int i = 0; i<cells.length; i++)
			{
				JPanel panel = new JPanel();
				JScrollPane pane;
				panel.setLayout (new BorderLayout());
				// check to make sure cells are not empty
				if(cells[i] != null)
				{
				panel.add(cells[i], BorderLayout.CENTER);

				this.cellController = new CellController(this);
				cells[i].addMouseListener(cellController);
				pane = new JScrollPane(panel);
				add(pane, BorderLayout.CENTER);
				}
				
				
			}
		}
		
	}
}
