import java.io.*;
import java.net.*;
import java.util.Date;
class storage implements Serializable{
	String reply;
	Date a;
	storage(){
		a = new Date();
	}
}
public class MyClient{
	public static void main(String[] args) throws IOException{
		String hostName = args[0];
		int portNumber = 5123;
 		Socket socket = new Socket(hostName, portNumber);		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));//this has to be put to a max of 140 chars
		String clientInput = null;
		clientInput = stdIn.readLine();
		storage now = new storage();
		now.reply = clientInput;
		out.writeObject(now);
//		out.newLine();
		out.flush();
		try{
			Thread.sleep(10000);
		} catch(InterruptedException a){
			System.out.println("some unknown misplaced error");
		}
		String serverReply = null;
		if((serverReply = in.readLine()) == null){
			System.out.println("Server is probably not running");
			socket.close();
		}
		else{
			if(serverReply.equals("Sure. Let us begin.")){
				System.out.println("Server:" + serverReply);
				while(true){
					clientInput = stdIn.readLine();
					now = new storage();
					now.reply = clientInput;
					if(clientInput.equals("End Chat")) break;
					out.writeObject(now);
				//	out.newLine();
					out.flush();
					serverReply = in.readLine();
					System.out.println("Server:" + serverReply);
				}
			}
			else{
				System.out.println("Wrong protocol");
			}
		}
	}
}
//this is code with only string being sent on both sides
/*public class MyClient{
	public static void main(String[] args) throws IOException{
		String hostName = args[0];
		int portNumber = 5123;
 		Socket socket = new Socket(hostName, portNumber);		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));//this has to be put to a max of 140 chars
		String clientInput = null;
		clientInput = stdIn.readLine();
		out.write(clientInput);
		out.newLine();
		out.flush();
		try{
			Thread.sleep(10000);
		} catch(InterruptedException a){
			System.out.println("some unknown misplaced error");
		}
			String serverReply = null;
		if((serverReply = in.readLine()) == null){
			System.out.println("Server is probably not running");
			socket.close();
		}
		else{
			if(serverReply.equals("Sure. Let us begin.")){
				System.out.println("Server:" + serverReply);
				while(true){
					clientInput = stdIn.readLine();
					if(clientInput.equals("End Chat")) break;
					out.write(clientInput);
					out.newLine();
					out.flush();
					serverReply = in.readLine();
					System.out.println("Server:" + serverReply);
				}
			}
			else{
				System.out.println("Wrong protocol");
			}
		}
	}
}
*/
