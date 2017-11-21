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
    int m = (int) Math.ceil(Math.sqrt(size));
    if(32 - this.mask <= m) {
      String sub;
      sub = subnets.get(subnets.size()-1).getAddr();
      subnet = new Subnet(sub,m);
      subnets.add(subnet);
    }
    return subnet;
  }

  /* (non-Javadoc)
 * @see model.IP#toString()
 */
@Override
  public String toString() {
    return String.format("%s subnets : %s", super.toString(), this.getSubnets().toString());
  }
  
  
}
