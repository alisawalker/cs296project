import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.ArrayList;



public class JChatComm extends JChatSession{
	ServerSocket server;
	Socket serverclient;
	Socket client;
	ObjectInputStream in;
	ObjectOutputStream out;
	BufferedReader stdIn;
	String ip;
	boolean closed;
	boolean thisisserver;
	JChatComm(boolean isserver, String hostName) throws IOException{
		closed = false;
		ip = hostName;
		if(isserver){
			thisisserver = true;
			server = new ServerSocket(5123);
			serverclient = server.accept();
			out = new ObjectOutputStream(new BufferedOutputStream(serverclient.getOutputStream()));
			out.flush();
			in = new ObjectInputStream(new BufferedInputStream(serverclient.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
		}
		else{
			thisisserver = false;
			client = new Socket(hostName, 5123);
//			System.out.println("time is ticking");
			out = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
//			System.out.println("everyone knows it");
			out.flush();
//			System.out.println("NO");
	//		in = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			BufferedInputStream al = new BufferedInputStream(client.getInputStream());
//			System.out.println("YUJH");
			in = new ObjectInputStream(al);
//			System.out.println("this is imp");
			stdIn = new BufferedReader(new InputStreamReader(System.in));
		}
	}

	public void sendMessage() throws IOException{
		if(!closed){
			String reply = stdIn.readLine();
			JPacket a = new JPacket();
			a.put(reply);
			out.writeObject(a);
			out.flush();
			if(thisisserver) addToLog(a,true);
			else addToLog(a,false);
		}
	}
	public void receiveMessage() throws IOException, ClassNotFoundException{
		if(!closed){
			JPacket b = (JPacket) in.readObject();
			if(!b.reply.string.equals("End Chat")) System.out.println(b.reply.string + " (" + b.a.toString() + ")");
			if(thisisserver){
				if(b.reply.string.equals("End Chat")){
//					System.out.println("Force quit?");
/*					String p = "closedummy";
					JPacket dummy = new JPacket();
					dummy.put(p);
					out.writeObject(dummy);
					out.flush();*/
					serverclient.close();
//					System.out.println("Close");
					server.close();
//					System.out.println("NOw");
					closed = true;
				}
			}
			if(!thisisserver){
				if(b.reply.string.equals("End Chat")){
					client.close();
					closed = true;
				}
			}
		           //this assumes that client doesn't send the message "End Chat" during conversation
			if(thisisserver) addToLog(b,false);
			else addToLog(b,true);
		}
	}
	public void endChat() throws IOException, ClassNotFoundException{
		if(!thisisserver){
			String endstring = "End Chat";
			JPacket a = new JPacket();
			a.put(endstring);
			out.writeObject(a);
			out.flush();
			closed = true;
			client.close();	
		}
	}
}