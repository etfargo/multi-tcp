package csci455_project1;

import java.io.*;
import java.net.*;

/**
 * This class is a connection based worker thread to display
 * the messages from the client and to tell the client a 
 * thread safe total of messages received.
 * @author elliotx250
 *
 */
public class ServerWorker implements Runnable{
	// private fields for finishing communications with the client
	private String 			 clientSentence = "";
	private Socket 			 connectionSocket;
	private BufferedReader 	 inFromClient;
    private DataOutputStream outToClient;
	private Counter 		 messages;
	
	public ServerWorker(Socket connectionSocket, 
				BufferedReader inFromClient, 
				DataOutputStream outToClient,
				Counter messages) {
		
		// instantiate the private fields
		this.connectionSocket 	= connectionSocket;
		this.inFromClient 		= inFromClient;
		this.outToClient 		= outToClient;
		this.messages 			= messages;
		
	}
	
	@Override
	public void run() {
		try {
			// maintain connection until exit received from client
			while ( !"exit".equals(clientSentence.toLowerCase().trim()) ) {
				
				clientSentence = inFromClient.readLine();
				
				// only increment count on non-exit messages
				if (!"exit".equals(clientSentence.toLowerCase().trim())) {
					messages.increment();
				}
				
				System.out.println("Message from [" + connectionSocket.getInetAddress()
					+":" + connectionSocket.getPort() + "]> " + clientSentence);
				
				outToClient.writeBytes("Total messages: " + messages.getTotal() + "\n");
				
			}
			
			// client has typed exit, so clean up and exit
			inFromClient.close();
			outToClient.close();
			connectionSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
