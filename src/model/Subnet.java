/**
 * 
 */
package model;

/**
 * @author Adrien
 *
 */
public class Subnet extends IP{
	
	private int[] firstIpHost;
	private int[] lastIpHost;
	private int[] broadcast;
	
	public Subnet() {
		super();
		this.firstIpHost = new int[] {0x00, 0x00, 0x00, 0x00};
		this.lastIpHost = new int[] {0x00, 0x00, 0x00, 0x00};
		this.broadcast = new int[] {0x00, 0x00, 0x00, 0x00};	
	}
	
	public Subnet(String addr, int mask) {
		super(addr, mask);
		this.firstIpHost = this.addAddr(1);
		this.lastIpHost = this.addAddr(this.getHosts());
		this.broadcast = this.addAddr(this.getHosts() + 1);
	}
	
	public String getFirstIpHost() {
		return String.format("%d.%d.%d.%d", 
				this.firstIpHost[0],
				this.firstIpHost[1],
				this.firstIpHost[2],
				this.firstIpHost[3]
			);
	}
	
	public String getLastIpHost() {
		return String.format("%d.%d.%d.%d", 
				this.lastIpHost[0],
				this.lastIpHost[1],
				this.lastIpHost[2],
				this.lastIpHost[3]
			);
	}
	
	public String getBroadcast() {
		return String.format("%d.%d.%d.%d", 
				this.broadcast[0],
				this.broadcast[1],
				this.broadcast[2],
				this.broadcast[3]
			);
	}
	
}
