package Server;

import java.io.*;
import java.net.*;
import java.util.*;

class Server implements Runnable{
	Socket connectionSocket;
	public static Vector clients = new Vector();
	
	public Server(Socket s) {
		try {
			System.out.println("Client got connected.  ");
			connectionSocket = s;
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void run() {
		try {
			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			BufferedWriter writer = 
					new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
			
			clients.add(writer);
			
			while(true) {
				String data1 = reader.readLine().trim();
				System.out.println("Received : "+data1);
				
				for(int i = 0; i<clients.size();i++) {
					try {
						BufferedWriter bw = (BufferedWriter)clients.get(i);
						bw.write(data1);
						bw.write("\r\n");
						bw.flush();
					}catch(Exception e){e.printStackTrace();}
				}
				
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static void main(String argv[])throws Exception{
		System.out.println("Threaded Chat server is Running");
		ServerSocket mysocket = new ServerSocket(5555);
		while(true) {
			Socket sock = mysocket.accept();
			Server server = new Server(sock);
			Thread serverThread = new Thread(server);
			serverThread.start();
		}
	}
}
