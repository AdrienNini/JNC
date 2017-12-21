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
	
	/**
	 * Constructor of the Model
	 */
	public ModelNetwork() {}
	
	/**
	 * Creates the network requested and stores it.
	 * @param i : Ip address of the network (String)
	 * @param m : mask of the network (int)
	 */
	public void createNetwork(String i, int m) {
		net = new Network(i, m);
	}
	
	/**
	 * Returns the network
	 * @return String : Network
	 */
	public String getNetwork() {
		return net.getAddr() + " " + net.getMask();
	}
	
	/**
	 * Requests the creation of each Subnet.
	 * @param sizes : size of each requested subnet
	 * @return boolean
	 */
	public boolean requestIp(ArrayList<Integer> sizes) {
		
		for (int size: sizes) {
			if(net.requestIP(size) == null) return false;
			this.setChanged();
		}
		this.notifyObservers(this.getSubnets());
		return true;
	}
	
	/**
	 * Returns the subnets of the network.
	 * @return ArrayList : all the subnet of the network
	 */
	public ArrayList<Subnet> getSubnets() {
		return net.getSubnets();
	}
	
	

}
