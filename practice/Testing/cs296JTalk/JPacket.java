package cs296JTalk;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.ArrayList;

public class JPacket implements Serializable{
	JMessage reply;
	Date a;
	JPacket(){
		reply = new JMessage();
		a = new Date();
	}
	void put(String input){
//		System.out.println(input);
	   reply.construct(input);
//	   System.out.println("Plz");
	}
}