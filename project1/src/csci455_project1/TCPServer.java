package csci455_project1;

import java.net.*;
import java.io.*;
/**
 * This TCPServer is currently multithreaded by utilizing my custom
 * ServerWorker class. It tracks total number of messages received
 * and doesn't have a coded limit of number of connections.
 * @author elliotx250
 *
 */
public class TCPServer {
	
	
	public static void main(String[] args) throws Exception{
		Counter 	 counter 		= new Counter();
		@SuppressWarnings("resource")
		ServerSocket welcomeSocket  = new ServerSocket(6789);
		System.out.println("The TCP server is on.");
		
		// server is always on
		while ( true ) {
			
			Socket connectionSocket = welcomeSocket.accept();
			
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			System.out.println("creating thread for [" + connectionSocket.getInetAddress()
				+"] on port: " + connectionSocket.getPort());
			
			// create the runnable server worker thread
			Runnable serverWorker = new ServerWorker(connectionSocket, inFromClient, outToClient, counter);
			Thread worker = new Thread(serverWorker);
			// start the thread.
			worker.start();
		}
	}
}
