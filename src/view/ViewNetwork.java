/*
 * Groupe 9 - Adrien Nini Pereira - Xavier De Beck
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
	
	protected static final String welcomeMsg = "╔═════════════════════════════════════════════════════════════════════════════╗\n" + 
											  "║  _    _      _                            _            ___ _   _ _____   _  ║\n" + 
											  "║ | |  | |    | |                          | |          |_  | \\ | /  __ \\ | | ║\n" + 
											  "║ | |  | | ___| | ___ ___  _ __ ___   ___  | |_ ___       | |  \\| | /  \\/ | | ║\n" + 
											  "║ | |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\      | | . ` | |     | | ║\n" + 
											  "║ \\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) | /\\__/ / |\\  | \\__/\\ |_| ║\n" + 
											  "║  \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  \\____/\\_| \\_/\\____/ (_) ║\n" + 
											  "║                                                                         v2.0║\n" + 
											  "╚═════════════════════════════════════════════════════════════════════════════╝\n" + 
											  "\n" + 
											  " Ce programme vous permet de calculer une table d’adressage rapidement et\n" + 
											  " simplement.\n" + 
											  " \n" + 
											  " ──────────────────────────────────────────────────────────────────────────────\n" + 
											  " ";
	
	protected static final String instructions = "INSTRUCTIONS :\n" + 
												"¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" + 
												"1. Entrez le nombre d'hôtes dont vous avez besoin pour votre réseau global\n" + 
												"2. Entrez le nombre d'hôtes dont vous avez besoin pour chaque sous-réseau\n" + 
												"3. Répétez l'étape 3 autant de fois que nécéssaire";
	
	protected ModelNetwork model;
	protected ControllerNetwork controller;
	
	/**
	 * Constructor for View.
	 * Adds the model and the controller.
	 * Adds it's on the Observers list of the model
	 * @param m : model
	 * @param c : controller
	 */
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
