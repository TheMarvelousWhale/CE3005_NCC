package udp;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class UDPClient {
	public static final String HOSTNAME = "";
	public static final int PORT = 17; //QOTD PORT
	public static final String MSG_SUBMIT = "Hoang Viet, LABGROUP, CLIENT IP";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		DatagramSocket clientSocket = new DatagramSocket(); //create an empty UDP socket 
		
		InetAddress IPAdress = InetAddress.getByName(HOSTNAME); //get that IP and do some processing if needed
		if (HOSTNAME == null || HOSTNAME.length() == 0)
			IPAdress = InetAddress.getLocalHost();
		
		//prepare the buffer
		byte[] tx_buf = new byte[1024];
		byte[] rx_buf = new byte[1024];
		
		int opt; //for continue or not
		while (true) {
			System.out.print("Say something to the server: ");
			String sentence = sc.nextLine();
			tx_buf = sentence.getBytes(); //get the string to bytes to send over socket
			DatagramPacket txPacket = new DatagramPacket(tx_buf,tx_buf.length,IPAdress,PORT); //wrap this bad boy with IP and Port number
			clientSocket.send(txPacket); //Throw it to IP layer, do we really care?
			
			if (sentence.contentEquals("BYE")) //J4F
			{
				break;
			}
			
			
			DatagramPacket rxPacket = new DatagramPacket(rx_buf,rx_buf.length);
			clientSocket.receive(rxPacket);
			//important to work with dirty buffer
			String ans = new String(rxPacket.getData(),0,rxPacket.getLength());
			System.out.println("Server says: "+ans);
			
			//some optional stuff
			System.out.println("Continue? ");
			opt = sc.nextInt();
			sc.nextLine();
			if (opt == 0)
				break;

		}
		System.out.println("Closing Connection.");
		clientSocket.close();
	}

}
