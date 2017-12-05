package view;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	private void printWelcome() {
		this.show(readFile(this.txtFolderPath + "welcomeMsg.txt"));
	}
	
	private void printInstructions() {
		this.show(readFile(this.txtFolderPath + "instructions.txt"));
	}
	
	private class ReadInput implements Runnable {

		@Override
		public void run() {
			while(true) {
				
			}
		}
		
	}

	@Override
	public void show(String string) {
		
		System.out.println(string);
		
	}
	
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
