import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.ArrayList;



public class JServer extends JChatComm{
/*	ServerSocket server;
	Socket client; //if we replace Socket by JClient, everywhere inplace of client, we have to put client.socket
	BufferedWriter out;
	ObjectInputStream in;
	BufferedReader stdIn;
	JServer(){
		server = new ServerSocket(5123);		
		client = server.accept();			
		out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())); //why do we put client here?
		in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
		stdIn = new BufferedReader(new InputStreamReader(System.in));
	}*/
	JServer() throws IOException{
		super(true, "dummy");
	}
	public void acceptConnection() throws IOException, ClassNotFoundException{
		JPacket inputLine = (JPacket) in.readObject();
//		System.out.println(inputLine.reply);
		if(inputLine.reply.string.equals("Free for a chat?")){
//			System.out.println("Client:" + inputLine.reply.string);
			String myReply = "Sure. Let us begin.";
			JPacket a = new JPacket();
			a.put(myReply);
			out.writeObject(a);
			out.flush();
//			System.out.println("this is ok");
		}
		else{
			System.out.println("Wrong protocol");
			server.close();
		}	
	}
}
//no jclient constructor in jchatcomm constructor