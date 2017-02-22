package ams.main;

import ams.model.facade.AMSFacade;
import ams.model.facade.AMSModel;
import ams.view.AMSMainView;

public class AMSDriver
{

	public static void main(String[] args)
	{
		// create a model
		AMSModel model = new AMSFacade();
		
		//create a view
		AMSMainView mainView = new AMSMainView(model);
		mainView.setVisible(true);
		
	}

}
