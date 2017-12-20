/**
 * 
 */
package view;

import java.util.Observer;

import controller.ControllerNetwork;
import model.ModelNetwork;

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
	
	/**
	 * Read a text file from a directory and return its content
	 * @param path : absolute path to the file
	 * @return String : the file content
	 */
	protected String readFile(String path) {
		return getClass().getClassLoader().getResource(path).getFile();
	}

}
