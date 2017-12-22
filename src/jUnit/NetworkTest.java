/*
 * Groupe 9 - Adrien Nini Pereira - Xavier De Beck
 */
package jUnit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import model.Network;
import model.Subnet;

/**
 * @author Adrien
 *
 */
public class NetworkTest {


	/**
	 * Test method for {@link model.Network#Network(java.lang.String, int)}.
	 */
	@Test
	public void testNetworkStringInt() {
		Network net = new Network("192.168.0.0", 24);
		assertEquals("192.168.0.0", net.getAddr());
		assertEquals("255.255.255.0", net.getMask());
		
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
	
	/**
	 * Test method for {@link model.Network#getSubnets()}.
	 */
	@Test
	public void testGetSubnets() {
		Network net = new Network("192.168.0.0", 24);
		net.requestIP(120);
		net.requestIP(50);
		net.requestIP(2);
		ArrayList<Subnet> subnets = net.getSubnets();
		// First Subnet
		assertEquals("192.168.0.0", subnets.get(0).getAddr());
		assertEquals("255.255.255.128", subnets.get(0).getMask());
		
		// Second Subnet
		assertEquals("192.168.0.128", subnets.get(1).getAddr());
		assertEquals("255.255.255.192", subnets.get(1).getMask());
		
		// Third Subnet
		assertEquals("192.168.0.192", subnets.get(2).getAddr());
		assertEquals("255.255.255.252", subnets.get(2).getMask());	
	}
	

}
