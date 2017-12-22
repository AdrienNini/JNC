package view;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ControllerNetwork;
import model.ModelNetwork;
import model.Subnet;

/**
 * @author Adrien
 *
 */
public class CLI extends ViewNetwork implements Observer {
	
	private Scanner sc;

	/**
	 * Class constructor
	 * @param m : model
	 * @param c : controller
	 */
	public CLI(ModelNetwork m, ControllerNetwork c) {
		super(m, c);
		
		this.update(null, null);
		this.sc = new Scanner(System.in);
		
		new Thread(new ReadInput()).start();
	}

	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			printWelcome();
			printInstructions();
		} else {
			makeTable(this.model.getSubnets());
		}
		
	}
	
	/**
	 * Prints the welcome banner on the console 
	 */
	private void printWelcome() {
		
		this.show(CLI.welcomeMsg);
	}
	
	/**
	 * Prints the instructions on the console 
	 */
	private void printInstructions() {
		this.show(CLI.instructions);
		this.show("Pour quitter le programme, tapez sur ctrl + C\n\n");
	}
	
	/**
	 * @author Adrien
	 * Main running Class
	 */
	private class ReadInput implements Runnable {
		
		private volatile boolean endProgram = false;
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			
			while(!endProgram) {
				
				int size = 0;
				
				// REQUEST THE NUMBER OF HOSTS NEEDED TO THE USER
				boolean isSizeOk = false;
				
				while (!isSizeOk) {
					show("Veuillez entrez Le nombre d'hôtes necéssaires :");
					try {
						size = sc.nextInt();
						if (0 < size) {
							if(isSizeOk = confirm("Confirmez-vous le nombre d'hôtes suivant ? : " + size)) {
								show("Nombre d'hôtes confirmé !\n");
							} 
						} else {
							show("Le nombre d'hôtes doit être suppérieur à 0 !\n");
						}
					} catch (InputMismatchException e) {
						show("Veuillez entrer un nombre d'hôtes correct !\n");
						sc.nextLine();
					}
					
					// Asking the controller to create the network
					if (!(isSizeOk = controller.requestISP(size))) {
						show("La requête a échoué, veuillez recommencer");
					}
				}
				
				
				
				// Asking the size for each subnet
				boolean nextSubnet = true;
				ArrayList<Integer> subSizes = new ArrayList<Integer>();
				int subSize;
				int i = 1;
				
				while (nextSubnet) {
					show("Entrez la nombre de hôtes pour le sous-réseau n°"+ i + " :");
					try {
						subSize = sc.nextInt();
						if (subSize > 0) {
							if (confirm("Confirmez-vous la taille suivante ? : " + subSize )) {
								subSizes.add(subSize);
								if (!(nextSubnet = confirm("Voulez-vous ajouter un autre sous-réseau ?"))) {
									if (!controller.requestIp(subSizes)) {
										show("Votre adressage est impossible ! Veuillez recommencer...\n");
										nextSubnet = true;
										i = 0;
									}
								}
							}
						} else {
							show("La taille doit être supérieure à 0 !\n");
						}
						i++;
					} catch (InputMismatchException e) {
						show("Veuillez entrer une taille correcte !\n");
						sc.nextLine();
					}
				}
			
				
				
				endProgram = true;
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see view.ViewNetwork#show(java.lang.String)
	 */
	@Override
	public void show(String string) {
		
		System.out.println(string);
		
	}
	
	/**
	 * Displays the addressing table
	 * @param subnets : list of all the subnets
	 */
	private void makeTable(ArrayList<Subnet> subnets) {
		
		this.show("\n\nTable d'adressage :\n-------------------");
		
		for (Subnet sub: subnets) {
			this.show(sub.toString());
		}
		
	}
	
	/**
	 * Asks to the users to confirm his input
	 * @param confirmMsg : ask confirmation message.
	 * @return boolean
	 */
	private boolean confirm(String confirmMsg) {
		this.show(confirmMsg + " [o/n]");
		while (true) {
			switch(sc.next()) {
			case "o":
				return true;
				
			case "n":
				return false;
				
			default:
				this.show("Veuillez entrez o ou n (en minuscule)");	
			}
		}
	}
	
}
