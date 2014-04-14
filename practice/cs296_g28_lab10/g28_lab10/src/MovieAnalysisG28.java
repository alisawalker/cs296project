import cs296MovieAnalysisPackage.*;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


class MovieAnalysisG28{
	public static void main(String[] args){
//		System.out.println(args.length);
		Movie a = new Movie(args[0]);
		MovieStatistics s = new MovieStatistics(a);
		FindActors f = new FindActors(a);
		System.out.println(String.format("%1s %2s", "Movie Name:", a.getMovieTitle()));
		System.out.println(String.format("%1s %2s", "Script Author(s):", a.getMovieAuthor()));
//		String[] b = a.getMovieWords();
	/*	int i = 0;
		while(i < b.length){
			System.out.println(b[i]);
			i++;
		}*/
//		System.out.println(s.countScriptWords());
//		System.out.println(s.countMovieCharacters());
//		List<Integer> n = s.determineCharGender();
/*		int i = 0;
		while(i < n.size()){
			System.out.println(n.get(i));
			i++;
		}*/
		s.printCharCountsWithGender();
		System.out.println(String.format("%1s %2s", "Hero:", f.findHero()));
		System.out.println(String.format("%1s %2s", "Heroine:", f.findHeroine()));
		System.out.println(String.format("%1s %2s","Villain:", f.findVillain()));
		FindGenre ab = new FindGenre();
		System.out.println(String.format("%1s %2s %3s","Genre:", ab.findGenre(args[0]).get(0), ab.findGenre(args[0]).get(1)));
	}
}
