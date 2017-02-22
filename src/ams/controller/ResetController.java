package ams.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



import ams.model.Program;
import ams.model.facade.AMSFacade;
import ams.model.facade.AMSModel;
import ams.view.AMSMainView;
import ams.view.Toolbar;

public class ResetController implements ActionListener
{
	private Toolbar toolbar;
	private AMSModel model;
	
	public ResetController(Toolbar toolbar)
	{
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{

		model = toolbar.getAMSMainView().getModel();

		
		
		int resetOption = JOptionPane.showConfirmDialog(null, "You sure you want to reset?!",
				null, JOptionPane.YES_NO_OPTION);
		
		if(resetOption == JOptionPane.OK_OPTION)
		{
			
			model.addProgram(new Program(null,null));
			toolbar.getAMSMainView().updateStatusBar("Please add a program");
			toolbar.getAMSMainView().updateGrid(null);
			toolbar.getAddCourse().setEnabled(false);
			toolbar.getRemoveCourse().setEnabled(false);
			toolbar.getReset().setEnabled(false);

		}

		
		
		
	}

}
