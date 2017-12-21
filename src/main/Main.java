/**
 * 
 */
package main;


import java.awt.EventQueue;

import controller.ControllerNetwork;
import model.ModelNetwork;
import view.*;

/**
 * @author Adrien
 *
 */
public class Main {
	
	private ModelNetwork m;
	private ControllerNetwork controlCLI;
	private ControllerNetwork controlGUI;
	private ViewNetwork cli;
	private ViewNetwork gui;
	
	
	/**
	 * Main class of the Program.
	 * Initializes all the infrastructure for the MVC Pattern
	 */
	public Main() {
		
		this.m = new ModelNetwork();
		
		this.controlCLI = new ControllerNetwork(this.m);
		this.controlGUI = new ControllerNetwork(this.m);
		
		this.cli = new CLI(this.m, this.controlCLI);
		this.gui = new GUI(this.m, this.controlGUI, 450, 300);
		
		this.controlCLI.addView(this.cli);
		this.controlGUI.addView(this.gui);
		
	}

	/**
	 * Main function of the program
	 * @param args : arguments passed at the beginning of program
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Main();	
			}
			
		});
		
		
	}

}
