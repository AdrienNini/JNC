/*
 * Groupe 9 - Adrien Nini Pereira - Xavier De Beck
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
	
	/**
	 * Constructor of Controller.
	 * Adds the model passed in arguments
	 * @param m : model
	 */
	public ControllerNetwork(ModelNetwork m) {
		this.model = m;
	}
	
	/**
	 * Requests the creation of a network to the model
	 * @param i : the ip address for the network
	 * @param m : the netmask for the network
	 */
	public void createNetwork(String i, int m) {
		model.createNetwork(i, m);
		view.show("Le réseau a correctement été configurer !");
	}
	
	/**
	 * Returns the network created by the model.
	 * @return String : network
	 */
	public String getNetwork() {
		return model.getNetwork();
	}
	
	/**
	 * Requests the creation of the subnets to the model.
	 * @param sizes : the size of each subnet requested
	 * @return boolean
	 */
	public boolean requestIp(ArrayList<Integer> sizes) {
		
		Collections.sort(sizes);
		Collections.reverse(sizes);
		
		return model.requestIp(sizes);
	}
	
	/**
	 * Requests to the ISP server a new ip address based on a number of hosts needed.
	 * @param size : Size of the network or number of hosts needed
	 * @return boolean
	 */
	public boolean requestISP(int size) {
		Client c;
		String[] requestedIP = null;
		try {
			c = new Client(5555, "localhost");
			requestedIP = c.request(size);
		} catch (IOException e) {
			return false;
		}
		
		model.createNetwork(requestedIP[0], Integer.parseInt(requestedIP[1]));
		view.show("L'adresse IP suivante vous a été attribuée : " + requestedIP[0] + "/" + requestedIP[1] + "\n");
		return true;
	}
	
	
	/**
	 * Adds the view to the controller.
	 * @param v : view
	 */
	public void addView(ViewNetwork v) {
		this.view = v;
	}
	
}
