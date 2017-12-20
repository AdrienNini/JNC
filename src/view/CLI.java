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
			makeTable(model.getSubnets());
		}
		
	}
	
	/**
	 * Prints the welcome banner on the console 
	 */
	private void printWelcome() {
		
		this.show(readFile("/txt/welcomeMsg.txt"));
	}
	
	/**
	 * Prints the instructions on the console 
	 */
	private void printInstructions() {
		this.show(readFile("/txt/instructions.txt"));
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
				
				String ip = null;
				int mask = 0;
				
				// REQUEST THE IP ADDRESS TO THE USER
				boolean isIpOk = false;
				
				while (!isIpOk) {
					show("Veuillez entrez votre adresse IPv4 :");
					ip = sc.next();
					if (ip.matches("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b")) { // Check if the input respects the forma
						if (isIpOk = confirm("Confirmez-vous l'adresse ip suivante ? " + ip)) {
							show("Adresse ip confirmée !\n");
						}
					} else {
						show("Veuillez entrez une adresse ip au bon format !\n");
					}
				}
				
				// REQUEST THE NETMASK TO THE USER
				boolean isMaskOk = false;
				
				while (!isMaskOk) {
					show("Veuillez entrez le masque au format CIDR (sans le slash) :");
					try {
						mask = sc.nextInt();
						if (0 < mask && mask <= 32) {
							if(isMaskOk = confirm("Confirmez-vous le masque suivant ? : " + mask)) {
								show("Masque confirmé !\n");
							} 
						} else {
							show("Le masque ne peut pas être inférieur à 0 ou supérieur a 32 !\n");
						}
					} catch (InputMismatchException e) {
						show("Veuillez entrer un masque correcte !\n");
						sc.nextLine();
					}
				}
				
				// Asking the controller to create the network
				controller.createNetwork(ip, mask);
				show("Vous avez le réseau : " + controller.getNetwork() + "\n");
				
				// Asking the size for each subnet
				boolean nextSubnet = true;
				ArrayList<Integer> sizes = new ArrayList<Integer>();
				int size;
				int i = 1;
				
				while (nextSubnet) {
					show("Entrez la nombre de hôtes pour le sous-réseau n°"+ i + " :");
					try {
						size = sc.nextInt();
						if (size > 0) {
							if (confirm("Confirmez-vous la taille suivante ? : " + size )) {
								sizes.add(size);
								if (!(nextSubnet = confirm("Voulez-vous ajouter un autre sous-réseau ?"))) {
									if (!controller.requestIp(sizes)) {
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
