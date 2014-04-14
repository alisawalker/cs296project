package cs296JTalk;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.ArrayList;

public class JChatSession{
	ArrayList<All> log;
	JChatSession(){
		log = new ArrayList<All>();
	}
	void addToLog(JPacket a, boolean server){
		All adder = new All();
		adder.packet = a;
		if(server) adder.owner = "Server";
		else adder.owner = "Client";
		log.add(adder);
	}
	public void printLog(){
		System.out.println("");
		System.out.println("==================MESSAGE LOG===============");
		System.out.println("");
		All b = new All();
		for(int i = 0; i < log.size(); i++){
			b = log.get(i);
			System.out.println(b.owner + ": " + b.packet.reply.string + " (" + b.packet.a + ")");
		}
	}
}


class All{
	JPacket packet;
	String owner;
}