/**
 * 
 */
package view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
		String out = ""; 
	    try { 
	      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(getPath() + path))); 
	      String line = ""; 
	       
	      while ((line =  reader.readLine()) != null) { 
	        out += line + "\n"; 
	      } 
	       
	      reader.close(); 
	    } catch (IOException e) { 
	      System.err.println("Error !"); 
	    } 
	    return out; 
		
	}
	
	/** 
	   * Returns the current working directory 
	   * @return String : path to working directory 
	   */ 
	  protected String getPath() { 
	    return System.getProperty("user.dir"); 
	  } 

}
