/* GROUPE n°9  */

/**
 * 
 */
package ISP;

/**
 * @author Adrien
 */
public class IP {
	
	//TODO: Update UML Diagram
	
	/**
	 * Ip address of the network in bytes
	 */
	protected int addr[];
	/**
	 * Network mask in CIDR form (e.g. /24, /16, ...)
	 */
	protected int mask;
	
	/**
	 * Default Constructor of IP.<br>
	 * By default, the addr is setted to 0.0.0.0<br>
	 * and the mask to 0
	 */
	public IP() {
		this.addr = new int[]{0x00, 0x00, 0x00, 0x00};
		this.mask = 0;
	}
	
	/**
	 * Constructor for IP.<br>
	 * Sets the values to the addr and mask
	 * @param addr String : IPv4 address
	 * @param mask int : The mask value in CIDR
	 */
	public IP(String addr, int mask) {
		String[] addrArray;
		addrArray = addr.split("\\.");
		this.addr = new int[] {
				Integer.parseInt(addrArray[0]), 
				Integer.parseInt(addrArray[1]), 
				Integer.parseInt(addrArray[2]), 
				Integer.parseInt(addrArray[3])
			};
		this.mask = mask;
	}
	
	/**
	 * Calculate the number of hosts for the network.
	 * @return int : max number of hosts possible
	 */
	public int getHosts() {
		
		return (int) Math.pow(2, 32-this.mask) - 2;
		
	}
	
	/**
	 * Return the number of hosts possible based on the parameter
	 * @param m : mask
	 * @return int : number of hosts possible
	 */
	public int getHosts(int m) {
		return (int) Math.pow(2, 32-m) - 2;
	}
	
	/**
	 * Returns the IP address in a String form.
	 * @return String : IPv4 address
	 */
	public String getAddr() {
		
		return String.format("%d.%d.%d.%d", this.addr[0], this.addr[1], this.addr[2], this.addr[3]);
	}
	
	/**
	 * Returns a String representing the Dotted Decimal Netmask
	 * @return String : Netmask in Dotted Decimal form
	 */
	public String getMask() {
		
		int[] m = calcMask();
		
		return String.format("%d.%d.%d.%d", m[0], m[1], m[2], m[3]);
	}
	
	public int getCIDR() {
		return this.mask;
	}
	
	
	/**
	 * Converts the mask from CIDR to DDN (Dotted Decimal Netmask).
	 * @return int[] : Array of ints 
	 */
	public int[] calcMask() {
		
		int[] m = new int[4];
		int tmpMask = this.mask;
		
		for (int i = 0; i < m.length; i++) {
			m[i] = (int) (tmpMask == 0 ? 0: tmpMask > 8 ? 255: 256 - Math.pow(2, 8 - tmpMask));
			tmpMask -= (tmpMask >= 8 ? 8: tmpMask);
		}
		
		return m;
	}
	
	/**
	 * Add an int to the Ip Address.
	 * @param n: int to add to the address
	 * @return int: the result of the addition
	 */
	public int[] addAddr(int n) {
			
		int[] tmpAddr = this.addr.clone();
		
		tmpAddr[3] += n;
		
		for (int i = tmpAddr.length-1; i >= 0; i--) {
			while (tmpAddr[i] > 255) {
				tmpAddr[i-1] += 1;
				tmpAddr[i] -= 255;
			}
		}
		
		return tmpAddr;
		
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("IP: %s; Mask: %s; Number Hosts possible: %d", 
				this.getAddr(), 
				this.getMask(), 
				this.getHosts()
			);
	}
	

}
