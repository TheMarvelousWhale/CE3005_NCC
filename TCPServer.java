package tcp_socket;
import java.io.*;
import java.net.*;
public class TCPServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String clientSentence;
		String modedSentence;
		ServerSocket welcomeSocket = new ServerSocket(TCPClient.PORT);
		while(true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			modedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(modedSentence);
		}
	}

}
