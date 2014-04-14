import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.ArrayList;


public class JMessage implements Serializable{
	String string;
	JMessage(){}
	void construct(String input){
		if(input.length() > 140){
			input = input.substring(0,139);
		}
		string = input;
	}
}