/**
 * 
 */
package jUnit;

import static org.junit.Assert.*;


import org.junit.Test;
import model.*;

/**
 * @author Adrien
 *
 */
public class NetworkTest {


	/**
	 * Test method for {@link model.Network#Network(java.lang.String, int)}.
	 */
	@Test
	public void testNetwork() {
		Network net = new Network("192.168.0.0", 24);
		assertEquals("192.168.0.0", net.getAddr());
		assertEquals("255.255.255.0", net.getMask());
		
	}

	/**
	 * Test method for {@link model.Network#getSubnets()}.
	 */
	@Test
	public void testGetSubnets() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Network#setSubnets(java.util.ArrayList)}.
	 */
	@Test
	public void testSetSubnets() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Network#requestIP(int)}.
	 */
	@Test
	public void testRequestIP() {
		Network net = new Network("192.168.0.0", 24);
		Subnet subnet = net.requestIP(26);
		
		assertEquals("192.168.0.0", subnet.getAddr());
		assertEquals("255.255.255.224", subnet.getMask());
		
		Subnet subnet2 = net.requestIP(8);
		assertEquals("192.168.0.32", subnet2.getAddr());
	}

}
