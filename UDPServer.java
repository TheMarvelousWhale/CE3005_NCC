package udp;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;


public class UDPServer {
	
		public static final int PORT = 17; //QOTD PORT
		
		public static void main(String args[]) throws Exception {
			
			DatagramSocket serverSocket = new DatagramSocket(PORT);
			byte[] rx_buf = new byte[1024];
			byte[] tx_buf = new byte[1024];
			while (true) {
				DatagramPacket rxPacket = new DatagramPacket(rx_buf,rx_buf.length);
				serverSocket.receive(rxPacket);
				String sentence = new String(rxPacket.getData(),0,rxPacket.getLength());
				//Arrays.fill(rx_buf, 0, sentence.length()-1, (byte) 0);
				System.out.println("Client says: "+sentence);
				if (sentence.equals("BYE") == true ) {
					System.out.println("Client has sent FIN."); //fking TCP
					break;
				}
				else {
					System.out.println("Length: "+sentence.length());
				}
				InetAddress IPAddress = rxPacket.getAddress();
				int port = rxPacket.getPort();
				Scanner sc = new Scanner(System.in);
				System.out.print("Say something to the client: ");
				String msg = sc.nextLine();
				tx_buf = msg.getBytes();
				DatagramPacket txPacket = new DatagramPacket(tx_buf,tx_buf.length,IPAddress,port);
				serverSocket.send(txPacket);
			}
			serverSocket.close();
		}
}
