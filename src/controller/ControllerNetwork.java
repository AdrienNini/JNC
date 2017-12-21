/**
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.ModelNetwork;
import socket.Client;
import view.ViewNetwork;

/**
 * @author Adrien
 *
 */
public class ControllerNetwork {

	private ModelNetwork model;
	private ViewNetwork view;
	
	public ControllerNetwork(ModelNetwork m) {
		this.model = m;
	}
	
	public void createNetwork(String i, int m) {
		model.createNetwork(i, m);
		view.show("Le réseau a correctement été configurer !");
	}
	
	public String getNetwork() {
		return model.getNetwork();
	}
	
	public boolean requestIp(ArrayList<Integer> sizes) {
		
		Collections.sort(sizes);
		Collections.reverse(sizes);
		
		return model.requestIp(sizes);
	}
	
	public boolean requestISP(int size) {
		Client c;
		String[] requestedIP = null;
		try {
			c = new Client(5555, "localhost");
			requestedIP = c.request(size);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.createNetwork(requestedIP[0], Integer.parseInt(requestedIP[1]));
		view.show("L'adresse IP suivante vous a été attribuée : " + requestedIP[0] + "/" + requestedIP[1] + "\n");
		return true;
	}
	
	public void addView(ViewNetwork v) {
		this.view = v;
	}
	
}
