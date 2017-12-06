/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Adrien
 *
 */
public class ModelNetwork extends Observable {
	
	private Network net;
	
	public ModelNetwork() {
		
	}
	
	public void createNetwork(String i, int m) {
		net = new Network(i, m);
	}
	
	public String getNetwork() {
		return net.getAddr() + " " + net.getMask();
	}
	
	public boolean requestIp(ArrayList<Integer> sizes) {
		
		for (int size: sizes) {
			if(net.requestIP(size) == null) return false;
			this.setChanged();
		}
		this.notifyObservers(net.getSubnets());
		return true;
	}
	
	public ArrayList<Subnet> getSubnets() {
		return net.getSubnets();
	}
	
	

}
