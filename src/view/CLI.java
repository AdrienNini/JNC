/**
 * 
 */
package view;

import java.util.Observable;
import java.util.Observer;

import controller.ControllerNetwork;
import model.ModelNetwork;

/**
 * @author Adrien
 *
 */
public class CLI extends ViewNetwork implements Observer {

	public CLI(ModelNetwork m, ControllerNetwork c) {
		super(m, c);
		update(null, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		printWelcome();
	}
	
	private void printWelcome() {
		System.out.println("Welcome to JNC !");
	}

	@Override
	public void show(String string) {
		
		
	}
	
}
