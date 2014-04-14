package cs296MovieAnalysisPackage;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


/**
*<h1> Movie Class </h1>
*<p> This class is the base class</p>
*/
public class Movie{
	protected String movieFilename;
	private BufferedReader movieBuffered;
	/**
	*<h1> Constructor of Movie class </h1>
	*This requires path of script file as String to be given as input and generates an object of Movie class
	*/
	public Movie(String args){
		movieFilename = args;
		try{
			movieBuffered = new BufferedReader(new FileReader(args));
			movieBuffered.mark(0);
		} 
		catch (FileNotFoundException a){
			System.out.println("File not found");
		}
		catch(IOException a){
			System.out.println("No lines in file");
		}
	}
	/**
	*<h1> Movie Title </h1>
	*<p> This method returns the title of the movie based on the script as a String</p>
	*/
	public String getMovieTitle(){
		int count = 0;
		String line = null;
		while(count != 1){
			try{
				line = movieBuffered.readLine();
				int i = 0;
				while(i < line.length()){
					if(line.charAt(i) == '\t' || line.charAt(i) == ' ' || line.charAt(i) == '\n'){i++; continue;}
					else{
						movieBuffered.close();
						movieBuffered = new BufferedReader(new FileReader(movieFilename));
						return line.substring(i);
//							count++;
//							break;
						}
					}
				} catch(IOException a){
				System.out.println("Line not found");
			}
		}
//			System.out.println(line);
//			count++;
		return "nothing";
	}
	/**
	*<h1> Script Authors </h1>
	*<p> This method returns the author(s) of the script as a String</p>
	*/
	public String getMovieAuthor(){
		int count = 0;
		String line = null;
		while(count <= 2){    //this is doubtful
			try{
				line = movieBuffered.readLine();
				int i = 0;
				while(i < line.length()){
					if(line.charAt(i) == '\t' || line.charAt(i) == ' ' || line.charAt(i) == '\n'){i++; continue;}
					else{
						if(line.substring(i,i+2).equals("by") || line.substring(i,i+10).equals("Written by") || line.substring(i,i+10).equals("Written By")){
							count++;
							break;
						}
						if(count == 2){
							movieBuffered.close();
							movieBuffered = new BufferedReader(new FileReader(movieFilename));
							return line.substring(i);
						}
						else{
							count++;
							break;
						}
					}
				}
			}
			catch(IOException a){
				System.out.println("Line not found");
			}
		}
		return "nothing";
	}
	/**
	*<h1> Print Title </h1>
	*<p> This method prints the title of the movie</p>
	*/
	public void printMovieTitle(){
		System.out.println(this.getMovieTitle());
	}
	/**
	*<h1> Print Authors </h1>
	*<p> This method prints the author(s) of the script</p>
	*/	
	public void printMovieAuthor(){
		System.out.println(this.getMovieAuthor());
	}
	/**
	*<h1> Movie Words </h1>
	*<p> This method returns all the words present in the script as a String array</p>
	*/
	public String[]  getMovieWords(){
		try{
			StringBuffer fileData = new StringBuffer();
        	BufferedReader reader = new BufferedReader(new FileReader(movieFilename));
        	char[] buf = new char[1024];
        	int numRead=0;
        	while((numRead=reader.read(buf)) != -1){
        	    String readData = String.valueOf(buf, 0, numRead);
        	    fileData.append(readData);
        	}
        	reader.close();
        	String s = fileData.toString();
			return s.split("[-.()\n\t, !?&:\"]+");
		}
		catch(FileNotFoundException a){
			System.out.println("File not found");
		}
		catch(IOException a){
			System.out.println("Line not found or something else");
		}
		String[] a = null;
		return a;

	}
}
