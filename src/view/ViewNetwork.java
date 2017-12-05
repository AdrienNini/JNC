/**
 * 
 */
package view;

import java.util.Observer;

import model.ModelNetwork;
import controller.ControllerNetwork;

/**
 * @author Adrien
 *
 */
public abstract class ViewNetwork implements Observer {
	
	protected ModelNetwork model;
	protected ControllerNetwork controller;
	
	public ViewNetwork(ModelNetwork m, ControllerNetwork c) {
		this.model = m;
		this.controller = c;
		
		this.model.addObserver(this);
	}

	/**
	 * Display information in the console
	 * @param string: information to display
	 */
	public abstract void show(String string);

}
