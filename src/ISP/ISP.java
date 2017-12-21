package ISP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
            
            int mask;
            try {
            	ISP server = new ISP(Integer.parseInt(args[0]));
		while(true){
                    server.numberHost = Integer.parseInt(server.waitForMessage());
                    mask =  (int) Math.ceil(Math.log10(server.numberHost) / Math.log10(2.));
                    for(int i = 0; i < ipList.length; i++){
                        if(Integer.parseInt(ipList[i].getMask()) > mask){
                            server.sendMessage(ipList[i-1]);
                            System.out.println(ipList[i-1]);
                        }
                    }
                }
			
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (IOException e) {
            // TODO Auto-generated catch blockµ
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
		ServerSocket severSocket = new ServerSocket(port);
		socket = severSocket.accept();
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(
        	new BufferedWriter(
        		new OutputStreamWriter(socket.getOutputStream())), true);
	}
        
        /**
         * wait a messege from the application for the numbre of of the user
         * @return
         * @throws IOException 
         */
	public String waitForMessage() throws IOException {
		String str = in.readLine();
		return str;
        }
        
        /**
         * send a ip at the software ask a new ip for a network
         * @param ip : ip for a new network
         */
        public void sendMessage(IP ip) {
		out.println(ip);
	}
}
