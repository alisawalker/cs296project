import java.io.*;
import java.net.*;

public class MyServer{
	public static void main(String[] args) throws IOException{
		int portNumber = 5123;
		ServerSocket server = new ServerSocket(portNumber);
		Socket client = server.accept();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())); //why do we put client here?
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		try{
			storage inputLine = (storage) in.readObject();
		
		boolean chatting = false;
		String myReply = null;
		if(inputLine.reply.equals("Free for a chat?")){
			System.out.println("Client:" + inputLine.reply);
			myReply = stdIn.readLine();		
			out.write(myReply);
			out.newLine();
			out.flush();
			chatting = true;
		}
		else{
			System.out.println("Wrong protocol");
		}
		storage clientReply = null;
		if(chatting){
			while(true){
				try{
					clientReply = (storage)  in.readObject();
				} catch(ClassNotFoundException c){
					System.out.println("Class not found");
				}
				if(clientReply.reply.equals("End Chat")) break;
				System.out.println("Client:" + clientReply.reply + "(" + clientReply.a.toString() + ")");
				myReply = stdIn.readLine();
				out.write(myReply);
				out.newLine();
				out.flush();
			}
		}
	} catch(ClassNotFoundException c){
			System.out.println("Class is not found");
		}
	}
}


//This is the initial code corresponding to initial code of client
/*
public class MyServer{
	public static void main(String[] args) throws IOException{
		int portNumber = 5123;
		ServerSocket server = new ServerSocket(portNumber);
		Socket client = server.accept();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())); //why do we put client here?
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = in.readLine();
		boolean chatting = false;
		String myReply = null;
		if(inputLine.equals("Free for a chat?")){
			System.out.println("Client:" + inputLine);
			myReply = stdIn.readLine();		
			out.write(myReply);
			out.newLine();
			out.flush();
			chatting = true;
		}
		else{
			System.out.println("Wrong protocol");
		}
		String clientReply = null;
		if(chatting){
			while(!(clientReply = in.readLine()).equals("End Chat")){
				System.out.println("Client:" + clientReply);
				myReply = stdIn.readLine();
				out.write(myReply);
				out.newLine();
				out.flush();
			}
		}
	}
}
*/
