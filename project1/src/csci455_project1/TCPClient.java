package csci455_project1;

import java.net.*;
import java.io.*;
/**
 * Single thread client based on Lu Liu's example code, but it 
 * maintains it's connection until user enters "exit"
 * @author elliotx250
 *
 */
public class TCPClient {

	public static void main(String[] args) throws Exception{
		
		String 			 sentence 		= "";
		String 			 modifiedSentence;
		BufferedReader 	 inFromUser 	= new BufferedReader(new InputStreamReader(System.in));
		Socket 			 clientSocket	= new Socket("localhost", 6789); //127.0.0.1
		DataOutputStream outToServer 	= new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader 	 inFromServer 	= new BufferedReader(new InputStreamReader(
											  clientSocket.getInputStream()));
		
		System.out.print("The TCP client is on. ");
		// allow input until user wishes to exit
		while ( !"exit".equals(sentence.toLowerCase().trim()) ) {
			
			System.out.println("Please enter your input:");
			
			sentence = inFromUser.readLine();
	
			outToServer.writeBytes(sentence + '\n');
	
			modifiedSentence = inFromServer.readLine();
	
			System.out.println(modifiedSentence);
	
		}
		// upon exit, wait 2 sec and close connection.
		System.out.println("Disassembling airplane");
		Thread.sleep(2000);
		System.out.println("Connection Closed");
		clientSocket.close();

	}
}
