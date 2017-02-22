package ams.view;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SubView extends JPanel
{

	private AMSMainView amsMainView;
	
	
	public SubView(AMSMainView amsMainView)
	{
		super();
		this.amsMainView = amsMainView;
	}
	
	public AMSMainView getAMSMainView()
	{
		
		return amsMainView;
	}


}
