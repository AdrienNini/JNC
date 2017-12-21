package ISP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ISP {


	private Socket socket;
	private ServerSocket serverSocket;
	private BufferedReader in;
	private PrintWriter out;
	private  int numberHost;
	
	
	
	public static void main(String[] args) {
		IP ipList [] = {
				new IP("10.0.0.0",8),
				new IP("172.16.0.0",16),
				new IP("192.168.0.0",24)
		};
		
		ISP server = null;
		try {
			server = new ISP(5555);
		} catch (IOException e) {
			System.out.println("Un serveur ISP est déjà lancé... fermeture...");
			System.exit(1);
		}
		
		try {
			server.numberHost = server.waitForMessage();
			
			for(int i = ipList.length-1; i >= 0; i--){
				
				if(ipList[i].getHosts() > server.numberHost){
					server.sendMessage(ipList[i].getAddr());
					server.sendMessage("" + ipList[i].getCIDR());
				}
				
			}
			
			server.closeConnection();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * creat a new server
	 * @param port: port use by the sever
	 * @throws IOException 
	 */
	public ISP(int port) throws IOException{
		connect(port);
	}

	/**
	 * connect the server
	 * @param port: port use by the sever
	 * @throws IOException 
	 */
	public void connect(int port)throws IOException{
		System.out.println("server creat on port: " + port);
		serverSocket = new ServerSocket(port);
		socket = serverSocket.accept();
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);
	}
	
	public void closeConnection() throws IOException {
		this.in.close();
		this.out.close();
		this.serverSocket.close();
	}

	/**
	 * wait a messege from the application for the numbre of of the user
	 * @return
	 * @throws IOException 
	 */
	public int waitForMessage() throws IOException {
		int msg = Integer.parseInt(in.readLine());
		return msg;
	}

	/**
	 * send a ip at the software ask a new ip for a network
	 * @param ip : ip for a new network
	 */
	public void sendMessage(String msg) {
		out.println(msg);
	}
}
