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
	
	
	private  Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private  int numberHost;
	private IP address;
	
	public static void main(String[] args) {
		IP ipList [] = new IP[3];
		ipList[0] = new IP("10.0.0.0",8);
		ipList[1] = new IP("172.16.0.0",12);
		ipList[2] = new IP("192.168.0.0",16);
		
		try {
			ISP server = new ISP(Integer.parseInt(args[0]));
			server.numberHost = Integer.parseInt(server.waitForMessage());
			int mask =  (int) Math.ceil(Math.log10(server.numberHost) / Math.log10(2.));
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ISP(int port) throws IOException{
		connect(port);
	}
	public void connect(int port)throws IOException{
		ServerSocket severSocket = new ServerSocket(port);
		socket = severSocket.accept();
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(
        	new BufferedWriter(
        		new OutputStreamWriter(socket.getOutputStream())), true);
	}
	public String waitForMessage() throws IOException {
		String str = in.readLine();
		return str;
        }
}
