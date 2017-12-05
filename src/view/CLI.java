package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
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
	private String welcomeMsg = 
			"╔═════════════════════════════════════════════════════════════════════════════╗\n" + 
			"║  _    _      _                            _            ___ _   _ _____   _  ║\n" + 
			"║ | |  | |    | |                          | |          |_  | \\ | /  __ \\ | | ║\n" + 
			"║ | |  | | ___| | ___ ___  _ __ ___   ___  | |_ ___       | |  \\| | /  \\/ | | ║\n" + 
			"║ | |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\      | | . ` | |     | | ║\n" + 
			"║ \\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) | /\\__/ / |\\  | \\__/\\ |_| ║\n" + 
			"║  \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  \\____/\\_| \\_/\\____/ (_) ║\n" + 
			"╚═════════════════════════════════════════════════════════════════════════════╝\n" + 
			" Ce programme vous permet de calculer un table d’adressage rapidement et\n" + 
			" simplement.";

	public CLI(ModelNetwork m, ControllerNetwork c) {
		super(m, c);
		this.update(null, null);
		this.sc = new Scanner(System.in);
		
		new Thread(new ReadInput()).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		printWelcome();
	}
	
	private void printWelcome() {
		this.show(this.welcomeMsg);
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
}
