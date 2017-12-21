/**
 * 
 */
package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Adrien
 *
 */
public class Client {

	private Socket soc;
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * Constructor of Client.
	 * Connects to the ISP server and do all the requests
	 * @param port : port to connect to
	 * @param addr : ip address to connect to (default localhost)
	 * @throws IOException : sends the exceptions from the connect function
	 */
	public Client(int port, String addr) throws IOException {
		connect(port, addr);
	}
	
	/**
	 * Sends a request message to the server.
	 * @param size : message to send to the server
	 * @return String[] : an array of two strings, the ip address and the mask
	 * @throws IOException : throws the exceptions from send and receive functions
	 */
	public String[] request(int size) throws IOException {
		
		send(size);
		
		return receive();
		
	}
	
	/**
	 * Sends a message to the server.
	 * @param size : message sent to the server, contains a size / number of hosts 
	 */
	public void send(int size) {
		out.println("" + size);
	}
	
	/**
	 * Receives the response of the server.
	 * @return String[] : an array of two strings, the ip address and the mask
	 * @throws IOException : throws the exceptions from the readLine function
	 */
	public String[] receive() throws IOException {
		String[] receivedInfo = new String[2];
		receivedInfo[0] = in.readLine();
		receivedInfo[1] = in.readLine();
		
		closeConnection();
		
		return receivedInfo;
	}
	
	/**
	 * Connect to the ISP server.
	 * @param port : port to connect to
	 * @param addr : ip address to connect to 
	 * @throws IOException : throws the exceptions from connection
	 */
	public void connect(int port, String addr) throws IOException {
		
		this.soc = new Socket(addr, port);
		this.in = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		this.out = new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(this.soc.getOutputStream())), true);
	}
	
	/**
	 * Closes all the connections (in, out, socket).
	 * @throws IOException : throws the exceptions form the closing of the connections
	 */
	public void closeConnection() throws IOException {
		this.in.close();
		this.out.close();
		this.soc.close();
	}
	
	
}
