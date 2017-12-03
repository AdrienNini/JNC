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
public class SubnetTest {

	/**
	 * Test method for {@link model.Subnet#getFirstIpHost()}.
	 */
	@Test
	public void testGetFirstIpHost() {
		Subnet sub = new Subnet("192.168.0.0", 26);
		assertEquals("192.168.0.1", sub.getFirstIpHost());
	}

	/**
	 * Test method for {@link model.Subnet#getLastIpHost()}.
	 */
	@Test
	public void testGetLastIpHost() {
		Subnet sub = new Subnet("192.168.0.0", 26);
		assertEquals("192.168.0.62", sub.getLastIpHost());
	}

	/**
	 * Test method for {@link model.Subnet#getBroadcast()}.
	 */
	@Test
	public void testGetBroadcast() {
		Subnet sub = new Subnet("192.168.0.0", 26);
		assertEquals("192.168.0.63", sub.getBroadcast());
	}

}
