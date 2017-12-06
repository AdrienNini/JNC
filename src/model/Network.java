package model;

import java.util.ArrayList;

/**
 * @author Xavier
 */

public class Network extends IP {
  
  protected ArrayList<Subnet> subnets;
  
  /**
 * Network contructor
 * @param addr : IP address of the network
 * @param mask : Network mask of the network (CIDR)
 */
public Network(String addr, int mask) {
    super(addr, mask);
    this.subnets = new ArrayList<Subnet>();
  }

  /**
 * Returns all the subnets the network knows 
 * @return ArrayList<Subnet> : array of the subnets
 */
public ArrayList<Subnet> getSubnets() {
    return subnets;
  }
  
  
  public void setSubnets(ArrayList<Subnet> subnets) {
    this.subnets = subnets;
  }
  
  
  /**
   * Create a new subnet 
   * if the subnets is imposible to create return null 
   * esle return the new subnet and add it in the arrayList
   * @param size : The size needed for the subnet
   */
  public Subnet requestIP(int size) {
    Subnet subnet = null;
    int m = (int) Math.ceil(Math.log10(size) / Math.log10(2.)); // Determine the number of bits needed for the hosts
    
    if (this.getHosts(32-m) < size) m++;
    int subMask = 32-m; // Determine the mask value
    
    if(this.getHosts() >= this.getHostsUsed() + this.getHosts(subMask)) { // Check if there's enough hosts
      String sub = this.getAddr();
      if (subnets.size() > 0) {
    	  	sub = subnets.get(subnets.size()-1).getNextSubnetAddr();
      }
      subnet = new Subnet(sub,subMask);
      subnets.add(subnet);
    }
    return subnet;
  }
  
  private int getHostsUsed() {
	  int totalHostsUsed = 0;
	  for(Subnet sub: subnets) {
		  totalHostsUsed += sub.getHosts();
	  }
	  return totalHostsUsed;
  }

  /* (non-Javadoc)
 * @see model.IP#toString()
 */
@Override
  public String toString() {
    return String.format("%s subnets : %s", super.toString(), this.getSubnets().toString());
  }
  
  
}
