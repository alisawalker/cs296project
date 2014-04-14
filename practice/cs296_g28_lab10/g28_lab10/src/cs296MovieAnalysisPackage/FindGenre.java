package cs296MovieAnalysisPackage;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
*<h1>Displays the Genre of the Movie Script</h1>
*Given a movie script using its tags it gives the genre of the movie
*/



public class FindGenre{
	String filepath;

	private List<String> action = new ArrayList<String>();
	private List<String> comedy = new ArrayList<String>();
	private List<String> romance = new ArrayList<String>();
	private List<String> horror = new ArrayList<String>();
	private List<String> sci_fi = new ArrayList<String>();
	private List<String> fantasy = new ArrayList<String>();
	private int ac;
	private int co;
	private int ro;
	private int ho;
	private int sc;
	private int fa;
	
	//int ac,co,ro,ho,sc,fa;
	//List<String> comedy = Arrays.asList("laugh","comedy","hillarious","silly","imitat","fun");
	//List<String> romance = Arrays.asList("kiss", "sex", "love you", "lov ","romance", "heart", "breakup");
	//List<String> horror = Arrays.asList("ghost", "horror", "fear", "scared','scaring");
/*	Horror = ['ghost', 'horror', 'fear', 'scared','scaring']
#H_count=[]
Sci_fi = ['alien', 'future', 'space', 'spaceship', 'clon', 'machi']
#S_count=[]
Fantasy= ['vampire','werewolf','werewolves','magic','spell','witch','wizard','wand']
#F_count=[]*/

	
	private void Initialise_genre(String y){
	
		filepath = y;
		//ArrayList<String> action = new ArrayList(Arrays.asList("gun" ,"wound", "freez", "soldier", "sword", "kung fu", "fight", "explod", "warrior"));
		action.add("gun"); action.add("wound"); action.add("freeze"); action.add("soldier") ; action.add("sword"); action.add("kung fu"); action.add("fight");
		action.add("explod"); action.add("warrior");

		comedy.add("laugh"); comedy.add("comedy"); comedy.add("hillarious");comedy.add("silly"); comedy.add("imitat");comedy.add("fun");

		romance.add("kiss"); romance.add("sex");romance.add("love you"); romance.add("lov");romance.add("romance"); romance.add("heart");romance.add("breakup");

		horror.add("ghost"); horror.add("horror");horror.add("fear");horror.add("scared");horror.add("scaring");

		sci_fi.add("alien");sci_fi.add("future");sci_fi.add("spaceship");sci_fi.add("space");sci_fi.add("clon");sci_fi.add("machi");

		fantasy.add("vampire");fantasy.add("werewolves");fantasy.add("magic");fantasy.add("spell");fantasy.add("witch");fantasy.add("wizard");fantasy.add("wand");
		ac=0;co=0;ro=0;ho=0;sc=0;fa=0;
		ac = total_counts_words(action);
		co = total_counts_words(comedy);
		ro = total_counts_words(romance);
		ho = total_counts_words(horror);
		sc = total_counts_words(sci_fi);
		fa = total_counts_words(fantasy);

	}

	private int total_counts_words(List<String> s){
		int p=0;

		try{
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			while (true) {
				String line = null;
				try{
					line=reader.readLine();

				}
				catch(IOException p1){
					System.out.println("File empty");
				}
				if (line == null){break;}
				p = p + count_words(line,s);
			}
		}
		catch(FileNotFoundException a){
			System.out.println("File not found");
		}
		return p;

	} 

	private int count_words(String l,List<String> u){
		int count =0;
		l = l.toLowerCase();
		for(int i=0;i<u.size();i++){
			int index=-1;
			index= l.indexOf(u.get(i));
			if (index != -1){
				count = 1 + count + count_words(l.substring(index+u.get(i).length()-1,l.length()),u);
			}
		}
		return count;
	}

	private int max(List<Integer> k){
		int m=0;
		for(int i=0;i<k.size();i++){
			if (k.get(i)>m) m=k.get(i);
		}
		return m;
	}
/**
*It calculates the weightage of each of the tags and outputs the top two tags.
		*@param y This is the filepath which it gets from the main file
		*/
	public List<String> findGenre(String y){

	
		Initialise_genre(y);
		List<String> t= new ArrayList<String>();
		List<Integer> i = new ArrayList<Integer>();
		i.add(ac);i.add(co);i.add(ro);i.add(sc);i.add(fa);
		int m = max(i);
		if (m==ac){
			i.set(0,0);
			ac=0;
			t.add("Action");
		}
		else if (m==co){
			i.set(1,0);
			co=0;
			t.add("Comedy");
		}
		else if (m==ro){
			i.set(2,0);
			ro=0;
			t.add("Romance");
		}
		else if (m==ho){
			i.set(3,0);
			ho=0;
			t.add("Horror");
		}	
		else if (m==sc){
			i.set(4,0);
			sc=0;
			t.add("Sci-fi");
		}
		else{
			i.set(5,0);
			fa=0;
			t.add("Fantasy");
		}
		int m1 = max(i);
		if (m1==ac){
			//i.set(0,0);
			t.add("Action");
		}
		else if (m1==co){
			//i.set(1,0);
			t.add("Comedy");
		}
		else if (m1==ro){
			//i.set(2,0);
			t.add("Romance");
		}
		else if (m1==ho){
			//i.set(3,0);
			t.add("Horror");
		}
		else if (m1==sc){
			//i.set(4,0);
			t.add("Sci-fi");
		}
		else{
			//i.set(5,0);
			t.add("Fantasy");
		}
		return t;


	}


}

/*class Extra{
	public static void main(String[] args) {
		FindGenre v = new FindGenre();
		List<String> s = new ArrayList<String>();
		s=v.tags();
		System.out.println(s.get(0));
		System.out.println(s.get(1));


	}	
}*/
