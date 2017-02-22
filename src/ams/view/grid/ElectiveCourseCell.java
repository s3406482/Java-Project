package ams.view.grid;

import java.awt.Color;

import javax.swing.border.LineBorder;

public class ElectiveCourseCell extends AbstractCourseCell
{

	public ElectiveCourseCell(String courseString)
	{
		super(courseString);
		
		//added this border just so text would be same size in both
		//elective and course cells
		this.setBorder(new LineBorder(Color.gray, 10));
		
	}

}
