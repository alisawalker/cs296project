import cs296JTalk.*;
import java.io.*;
class Main{
	public static void main(String[] args) throws IOException,ClassNotFoundException{
		if(args.length == 0){
			JServer a = new JServer();
//			a.acceptConnection();
			a.receiveMessage();
			a.sendMessage();
			a.receiveMessage();
			a.sendMessage();
			a.receiveMessage();
			a.sendMessage();
			a.receiveMessage();
			a.printLog();
		}
		else{
			JClient a = new JClient(args[0]);
			a.callServer();
			a.sendMessage();
			a.receiveMessage();
			a.sendMessage();
			a.receiveMessage();
			a.endChat();
			a.sendMessage();
			a.receiveMessage();
			a.printLog();

		}
	}
}




