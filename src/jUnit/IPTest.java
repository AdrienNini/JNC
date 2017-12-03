
/* GROUPE n°9  */

/**
 * 
 */
package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;
import model.IP;

/**
 * @author Adrien
 *
 */
/**
 * @author Adrien
 *
 */
/**
 * @author Adrien
 *
 */
public class IPTest extends IP{


	/**
	 * Test method for {@link model.IP#getHosts()}.
	 */
	@Test
	public void testGetHosts() {
		
		for (int i = 0; i < 33; i++) {
			//System.out.println(i);
			this.mask = i;
			assertEquals((int) Math.pow(2, 32-i) - 2, this.getHosts(), 0);
		}
		
	}

	/**
	 * Test method for {@link model.IP#getAddr()}.
	 */
	@Test
	public void testGetAddr() {
		this.addr = new int[] {192, 168, 0, 1};
		assertEquals("192.168.0.1", this.getAddr());
	}

	/**
	 * Test method for {@link model.IP#getMask()}.
	 */
	@Test
	public void testGetMask() {
		
		this.mask = 0;
		assertEquals("0.0.0.0", this.getMask());
		this.mask = 1;
		assertEquals("128.0.0.0", this.getMask());
		this.mask = 2;
		assertEquals("192.0.0.0", this.getMask());
		this.mask = 3;
		assertEquals("224.0.0.0", this.getMask());
		this.mask = 4;
		assertEquals("240.0.0.0", this.getMask());
		this.mask = 5;
		assertEquals("248.0.0.0", this.getMask());
		this.mask = 6;
		assertEquals("252.0.0.0", this.getMask());
		this.mask = 7;
		assertEquals("254.0.0.0", this.getMask());
		this.mask = 8;
		assertEquals("255.0.0.0", this.getMask());
		this.mask = 9;
		assertEquals("255.128.0.0", this.getMask());
		this.mask = 10;
		assertEquals("255.192.0.0", this.getMask());
		this.mask = 11;
		assertEquals("255.224.0.0", this.getMask());
		this.mask = 12;
		assertEquals("255.240.0.0", this.getMask());
		this.mask = 13;
		assertEquals("255.248.0.0", this.getMask());
		this.mask = 14;
		assertEquals("255.252.0.0", this.getMask());
		this.mask = 15;
		assertEquals("255.254.0.0", this.getMask());
		this.mask = 16;
		assertEquals("255.255.0.0", this.getMask());
		this.mask = 17;
		assertEquals("255.255.128.0", this.getMask());
		this.mask = 18;
		assertEquals("255.255.192.0", this.getMask());
		this.mask = 19;
		assertEquals("255.255.224.0", this.getMask());
		this.mask = 20;
		assertEquals("255.255.240.0", this.getMask());
		this.mask = 21;
		assertEquals("255.255.248.0", this.getMask());
		this.mask = 22;
		assertEquals("255.255.252.0", this.getMask());
		this.mask = 23;
		assertEquals("255.255.254.0", this.getMask());
		this.mask = 24;
		assertEquals("255.255.255.0", this.getMask());
		this.mask = 25;
		assertEquals("255.255.255.128", this.getMask());
		this.mask = 26;
		assertEquals("255.255.255.192", this.getMask());
		this.mask = 27;
		assertEquals("255.255.255.224", this.getMask());
		this.mask = 28;
		assertEquals("255.255.255.240", this.getMask());
		this.mask = 29;
		assertEquals("255.255.255.248", this.getMask());
		this.mask = 30;
		assertEquals("255.255.255.252", this.getMask());
		this.mask = 31;
		assertEquals("255.255.255.254", this.getMask());
		this.mask = 32;
		assertEquals("255.255.255.255", this.getMask());	
	}
	
	
	/**
	 * Test method for {@link model.IP#addAddr(int)}.
	 */
	@Test
	public void testAddAddr() {
		this.addr = new int[]{192, 168, 0, 16};
		assertEquals("192.168.2.6", this.addAddr(500));
	}
	
	/**
	 * Test method for {@link model.IP#toString()}
	 */
	@Test
	public void testToString() {
		this.addr = new int[] {192,168,0,1};
		this.mask = 24;
		
		assertEquals("IP: 192.168.0.1; Mask: 255.255.255.0; Number Hosts possible: 254", this.toString());
	}


}
