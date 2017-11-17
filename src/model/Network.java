package model;

import java.util.ArrayList;

/**
 * @author Xavier
 */

public class Network extends IP {
  
  protected ArrayList<Subnet> subnets;
  
  public Network(String addr, int mask, ArrayList<Subnet>[] subnets) {
    super(addr, mask);
    this.subnets = new ArrayList();
  }

  public ArrayList<Subnet> getSubnets() {
    return subnets;
  }
  
  
  public void setSubnets(ArrayList<Subnet> subnets) {
    this.subnets = subnets;
  }
  
  
  /**
   * Creat a new subnet 
   * if the subnets is imposible to creat return null esle return the new subnet and add this in the arrayList
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

  @Override
  public String toString() {
    return String.format("%s subnets : %s", super.toString(), this.getSubnets().toString());
  }
  
  
}
