/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.ModelNetwork;
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
		model.createNetwork("192.168.0.0", 24);
		view.show("L'adresse IP suivante vous a été attribuée : 192.168.0.0\n");
		return true;
	}
	
	public void addView(ViewNetwork v) {
		this.view = v;
	}
	
}
