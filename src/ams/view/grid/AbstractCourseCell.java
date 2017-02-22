package ams.view.grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ams.controller.CellController;


@SuppressWarnings("serial")
public abstract class AbstractCourseCell extends GridCell
{
	@SuppressWarnings("unused")
	private String courseString;
	private String courseCode;
	private JPanel panel;
	private JScrollPane pane;
	private CellController cellController;
	
	public AbstractCourseCell(String courseString)
	{
		this.setBackground(Color.gray);
		this.courseString = courseString;
		
		
		String[] courseDetails = courseString.split(":");
		String preReqs = null;
		String courseType;
		courseCode = courseDetails[0];
		String courseName = courseDetails[1];
		String creditPoints = courseDetails[2];
		if(courseDetails.length > 4)
		{
			preReqs = courseDetails[3];
			 courseType = courseDetails[4];
		}
		else{
			courseType = courseDetails[3];
		}
		

		this.setLayout(new GridLayout(10,1));

		JLabel line1 = new JLabel("Code: " + courseCode);
		this.add(line1);
		
		JLabel line2 = new JLabel("Title: " + courseName);
		this.add(line2);
		
		JLabel line3 = new JLabel("Credit Points: " + creditPoints);
		this.add(line3);
		
		JLabel line4 = new JLabel("Prereqs: " + preReqs);
		this.add(line4);
		
		JLabel line5 = new JLabel("Type: " + courseType);
		this.add(line5);
		

	}
	
	public String getCode()
	{
		return courseCode;
	}
}


		
