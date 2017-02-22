package ams.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import ams.view.AMSMainView;



public class WindowController implements WindowListener
{
	private AMSMainView amsMainView;

	public WindowController(AMSMainView amsMainView)
	{
		this.amsMainView = amsMainView;
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		int exitOption = JOptionPane.showConfirmDialog(null, 
				"You sure you want to exit??", null, JOptionPane.YES_NO_OPTION);
		if(exitOption == JOptionPane.OK_OPTION)
		{
			
			amsMainView.dispose();
			
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		
		
	}

	

}
