package ams.controller;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import ams.model.Program;
import ams.model.facade.AMSModel;

import ams.view.Toolbar;

public class AddProgramController implements ActionListener
{

	private JPanel panel;
	private Toolbar toolbar;
	private AMSModel model;

	private JTextField nameEntry = new JTextField(20);
	private LimitedJTextField codeEntry = new LimitedJTextField(6);
	
	
	
	public AddProgramController(Toolbar toolbar)
	{
		
		this.toolbar = toolbar;
	}
	




	@Override
	public void actionPerformed(ActionEvent arg0)
	{
	
		model = this.toolbar.getAMSMainView().getModel();
		
//
//		FOR TESTING!!!
//
//		model.addProgram(new Program("BP0625", "Bachelor of Computing Studies"));
		
		
		
		
		
		//create a new panel for the JOptionPane
		codeEntry = new LimitedJTextField(6);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,2,5,5));
		panel.add(new JLabel("Enter Program Code"));
	    panel.add(codeEntry);
	    panel.add(new JLabel("Enter Program Name"));
	    panel.add(nameEntry);
		
		int programInfo = JOptionPane.showConfirmDialog(null, panel, "Enter program info",
				JOptionPane.OK_CANCEL_OPTION);
		
		// if/else loops to check data entry
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
					
						toolbar.getAMSMainView().updateStatusBar(model.getProgram().toString());
						
//						updates of buttons and status bar
						toolbar.getAddCourse().setEnabled(true);
						toolbar.getRemoveCourse().setEnabled(true);
						toolbar.getReset().setEnabled(true);
						toolbar.getAMSMainView().getAmsMenu().getAddCourse().setEnabled(true);
						toolbar.getAMSMainView().getAmsMenu().getRemoveCourse().setEnabled(true);
						toolbar.getAMSMainView().getAmsMenu().getReset().setEnabled(true);
						toolbar.getAMSMainView().updateGrid(null);
						
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
			

		}
		
		nameEntry.setText(null);
		
		

	}


}
