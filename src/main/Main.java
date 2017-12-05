/**
 * 
 */
package main;

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
	private ViewNetwork cli;
	
	public Main() {
		
		this.m = new ModelNetwork();
		
		this.controlCLI = new ControllerNetwork(this.m);
		
		this.cli = new CLI(this.m, this.controlCLI);
		
		this.controlCLI.addView(this.cli);
		
	}

	/**
	 * Main function of the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Main();
		
	}

}
