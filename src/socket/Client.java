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
	
	public Client(int port, String addr) throws IOException {
		connect(port, addr);
	}
	
	public String[] request(int size) throws IOException {
		
		send(size);
		
		return receive();
		
	}
	
	public void send(int size) {
		out.println("" + size);
	}
	
	public String[] receive() throws IOException {
		String[] receivedInfo = new String[2];
		receivedInfo[0] = in.readLine();
		receivedInfo[1] = in.readLine();
		
		closeConnection();
		
		return receivedInfo;
	}
	
	public void connect(int port, String addr) throws IOException {
		
		this.soc = new Socket(addr, port);
		this.in = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		this.out = new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(this.soc.getOutputStream())), true);
	}
	
	public void closeConnection() throws IOException {
		this.in.close();
		this.out.close();
		this.soc.close();
	}
	
	
}
