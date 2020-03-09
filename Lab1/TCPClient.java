package tcp_socket;
import java.io.*;
import java.net.*;

public class TCPClient {
    public static final int PORT = 8000;
    private static final String hostname = "127.0.0.1";
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String sentence;
		String modedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));  //receive input from keyboard
		Socket clientSocket = new Socket(hostname,PORT);  //create new socket
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //prepare buffer to send to server
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //receive from server
		sentence = inFromUser.readLine();  
		outToServer.writeBytes(sentence+'\n');
		modedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: "+modedSentence);
		clientSocket.close();
	}

}
