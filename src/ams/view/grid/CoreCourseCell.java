package ams.view.grid;

import java.awt.Color;


import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class CoreCourseCell extends AbstractCourseCell
{


	public CoreCourseCell(String courseString)
	{
		super(courseString);
		this.setBorder(new LineBorder(Color.black, 10));
		
	}


}
