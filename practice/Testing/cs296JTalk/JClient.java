package cs296JTalk;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.ArrayList;



public class JClient extends JChatComm{
/*	Socket socket;
	BufferedReader in;
	ObjectOutputStream out;
	BufferedReader stdIn;
	JClient(String ip){
		socket = new Socket(ip, 5123);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		stdIn = new BufferedReader(new InputStreamReader(System.in));
	}*/
	public JClient(String hostName) throws IOException{ //why IOE?
		super(false, hostName);
//		System.out.println("hurr");
	}
	public void callServer() throws IOException,ClassNotFoundException{
		try{
			client.setSoTimeout(10000);
			String clientInput = "Free for a chat?";
//			System.out.println(clientInput);
			JPacket a = new JPacket();
//			System.out.println("first");
			a.put(clientInput);
//			System.out.println("point");
			out.writeObject(a);
			out.flush();
//			System.out.println("got it");
			JPacket s = (JPacket) in.readObject();
//			System.out.println(";lkhgoihg");
			if(!s.reply.string.equals("Sure. Let us begin.")){
				System.out.println("Wrong protocol");
				endChat();	
			}
			client.setSoTimeout(0);
		} catch(SocketTimeoutException ste){
			System.out.println("Server doesn't respond");
			endChat();
//			System.out.println("no infoy");
		}
	}
}
