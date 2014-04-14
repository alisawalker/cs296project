package cs296MovieAnalysisPackage;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


/**
* <h1> FindActors class</h1>
* This class gives information about the movie like Hero, Heroine, Villain and genre of movie
*/
public class FindActors extends MovieStatistics{
	/**
	* <h1> Constructor </h1>
	* This is constructor of FindActors class and takes a Movie object as an argument
	*/	
	public FindActors(Movie a){
		super(a);
	}
	/**
	*<h1>Hero</h1>   
	* This function returns hero of the script as a String
	*/
	public String findHero(){
		int max = 0;
		for(int i = 0; i< charcounts.size(); i++){
			if(charcounts.get(max) < charcounts.get(i) && chargenders.get(i).equals("Male")){
				max = i;
			}
		}
		return p.get(max);
	}
	/**
	*<h1>Heroine</h1>
	*<p> This function returns heroine of the script as a String</p>
	*/
	public String findHeroine(){
		int max = 0;
		for(int i = 0; i< charcounts.size(); i++){
			if(charcounts.get(max) < charcounts.get(i) && chargenders.get(i).equals("Female")){
				max = i;
			}
		}
		return p.get(max);
	}
	/**
	*<h1>Villain</h1>
	*<p> This function returns villain of the script as a String </p>
	*/
	public String findVillain(){
		List<Integer> a = villain_list();
	//	System.out.println(a);
		int max = 0;
		for(int i = 0; i < p.size(); i++){
			if(p.get(i).equals(findHero()) || p.get(i).equals(findHeroine())) continue;
			if(a.get(max) < a.get(i)){
				max = i;
			}
		}
		return p.get(max);
	}
						

}
