package view;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ControllerNetwork;
import model.ModelNetwork;

/**
 * @author Adrien
 *
 */
public class CLI extends ViewNetwork implements Observer {
	
	private Scanner sc;
	private String txtFolderPath = "/Users/Adrien/OneDrive - EPHEC asbl/Cours/Dev. informatique - Application/Pratique/PROJET/JNC/txt/";

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

	@Override
	public void update(Observable o, Object arg) {
		printWelcome();
		printInstructions();
	}
	
	/**
	 * Prints the welcome banner on the console 
	 */
	private void printWelcome() {
		this.show(readFile(this.txtFolderPath + "welcomeMsg.txt"));
	}
	
	/**
	 * Prints the instructions on the console 
	 */
	private void printInstructions() {
		this.show(readFile(this.txtFolderPath + "instructions.txt"));
	}
	
	private class ReadInput implements Runnable {

		@Override
		public void run() {
			boolean endProgram = false;
			boolean isIpOk = false;
			boolean isMaskOk = false;
			
			while(!endProgram) {
				String ip;
				int mask;
				
				// REQUEST THE IP ADDRESS TO THE USER
				while (!isIpOk) {
					show("Veuillez entrez votre adresse IPv4 :");
					ip = sc.next();
					if (ip.matches("\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b")) {
						if (isIpOk = confirm("Confirmez-vous l'adresse ip suivante ? " + ip)) {
							show("Adresse ip confirmée !\n");
						}
					} else {
						show("Veuillez entrez une adresse ip au bon format !\n");
					}
				}
				
				// REQUEST THE NETMASK TO THE USER
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
						show("Veuillez entrez un masque correcte !\n");
					}
					
				}
				
				
				show("End of program...");
				endProgram = true;
			}
			
		}
		
	}

	@Override
	public void show(String string) {
		
		System.out.println(string);
		
	}
	
	
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
	
	/**
	 * Read a text file from a directory and return its content
	 * @param path : absolute path to the file
	 * @return String : the file content
	 */
	private String readFile(String path) {
		String out = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
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
}
