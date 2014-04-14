package cs296MovieAnalysisPackage;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
*<h1> Data </h1>
*It is a data structure which holds the name,character's count and its gender.
*/


class Data{
    String name;
    int character_count;
    String gender;
    public void initialise(String n,int c,String g){
        name =n;
        character_count = c;
        gender = g;
    }

}




class Help{
	String filepath;
	/**
	*stores the number of lines in the file
	*/
	protected int no_lines_file;
	protected Help(String given){
		filepath = given;
		movie_characters();
	}
	/**
	*stores all the characters in Uppercase
	*/
	protected List<String> p = new ArrayList<String>();
	/**
	*stores all the characters in Lowercase
	*/
	protected List<String> q = new ArrayList<String>();		

	protected void movie_characters(){
		no_lines_file = 0;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			while (true) {
				String line = null;
				try{
					line=reader.readLine();
					no_lines_file++;
				}
				catch(IOException p){
					System.out.println("File empty");
				}
				if (line == null){break;}
				boolean b = Maybeacharacter(line);

				if (b){
					line = modify(line);

					//System.out.println(line);
					p.add(line);
				}
			}

			p = removeduplicate(p);
			/////////////////////////This is the added code ////////////////////////////////////////////////////
			List<String> r = new ArrayList<String>();
			int i = 0;
			Movie myEdit = new Movie(filepath);
			while(i < p.size()){
/*				if(p.equals("FANTASTIC FOUR") || p.equals("THE THREE MUSKETEERS") || p.equals("KUNG FU PANDA")){
					i++;
					continue;
				}*/
				if(!(p.get(i).contains("INT") || p.get(i).contains("EXT") || p.get(i).contains("ROOF TOP") || p.get(i).contains("SCENE") || p.get(i).contains("SCREEN") || p.get(i).contains("OPEN") || p.get(i).contains("DRAFT") || p.get(i).contains("NARRATOR") || p.get(i).contains("INCLUDE") || p.get(i).contains("END") || p.get(i).contains("OTHERS") || p.get(i).contains("AT") || p.get(i).contains("--") || p.get(i).contains("BACK") || p.get(i).contains("TRAIN") || p.get(i).contains("EVERYTHING") || p.get(i).contains("CONTINUOUS") || p.get(i).contains("ON") || p.get(i).contains("AUTOMATED") || p.get(i).contains("THING") || p.get(i).contains("VOICE") || p.get(i).contains("OFFICE") || p.get(i).contains("FIGURE") || p.get(i).contains("CUTBACKS") || p.get(i).contains("AND"))) {
					r.add(p.get(i));
				}
				i++;
			}
			p = r;			
			//////////////////////////Here ends the added code /////////////////////////////////////////
			q = searchstring(p);
/*			int k=0;
			while(k!= p.size()){
				System.out.println(p.get(k));
				k++;
			}
			System.out.println(p.size());
*/
		}
		catch(FileNotFoundException a){
			System.out.println("File not found");
		}

		
	}
	private boolean Maybeacharacter(String p){
		int letterscount=0;
		int capitalcount=0;
		char temp;
		for(int i=0;i<p.length();i++){
			temp=p.charAt(i);
			if(temp != ' '){
				if(!(Character.isDigit(temp) || (temp=='.') || (temp=='(') || (temp == ':') || (temp == '!') || (temp == '?') || (temp == '-') || (temp == ','))){
					if(Character.isLetter(temp)){
						letterscount++;
						if(Character.isUpperCase(temp)){
							capitalcount++;
						}
					}
				}
				else return false;
			}
		}
		return (letterscount==capitalcount) && (letterscount!=0);
	}

	private String modify(String s){
		int y;
		int end;
		for(y=0;y<s.length()-1;y++){
			if(s.charAt(y)!=' '){break;}
		}
		for(end= s.length()-1;end>0;end--){
			if(s.charAt(end)!=' '){break;}

		}
		return s.substring(y,end+1);

	}

	private List<String> removeduplicate(List<String> w){
		List<String> final_capital = new ArrayList<String>();
		for(int i=0;i<w.size()-1;i++){
			boolean b = true;
			for(int j=0; j<i;j++){
				b = b && (!w.get(i).equals(w.get(j)));
				//System.out.println(b);
				if(!b) break;
			}
			if (b){ final_capital.add(w.get(i));}
		}
		return final_capital;
	}

	private List<String> searchstring(List<String> w){
		List<String> for_search = new ArrayList<String>();
		for(int i=0; i<w.size();i++){
			boolean convert =false;
			boolean special = false;
			int position = 0;
			String m = w.get(i);
			String q="";
			for(int j=0;j<m.length();j++){
				if (!convert){
					convert = !convert;
					q=q+m.substring(j,j+1);
				}
				else{
					if (m.substring(j,j+1).equals("'") && m.substring(j-1,j).equals("D")){
						convert = false;
						special = true;
						position = j;
						q=q+m.substring(j,j+1);		
					}
					else if (m.substring(j,j+1).equals(" ")){
						convert = false;
						q=q+m.substring(j,j+1);
					}
					else q=q+m.substring(j,j+1).toLowerCase();
				}
			}
			for_search.add(q);
			if(special){
				String additonal= q.substring(0,position-1)+q.substring(position-1,position).toLowerCase()+q.substring(position,q.length());
				for_search.add(additonal);
			}
		}
		return for_search;
	}
	/**
	*It adds the counts of the lowercase strings of different types (as needed by the movie script)
	*present in the variable q to their respective uppercase strings.
	*@return List<Integer> It is the list with the counts of uppercase strings.
	*/
	protected List<Integer> final_count_for_gender (List<Integer> n){
		List<String> q1 = new ArrayList<String>();
		List<Integer> to_return = new ArrayList<Integer>();
//		System.out.println(n.size());
//		System.out.println(q.size());
		for(int i=0;i<p.size();i++) to_return.add(0);
		for(int i=0; i<q.size();i++){
			q1.add(q.get(i).toUpperCase());
		}
//		System.out.println(q.size());
//		System.out.println(n.size());
//		System.out.println("nothing");
		for(int i=0;i<q.size();i++){
			for(int j=0;j<p.size();j++){
				if(q1.get(i).equals(p.get(j))){
					to_return.set(j,to_return.get(j)+n.get(i));
					break;
				}
			}
		}
		return to_return;

	}



}






////////////////////////////////////////////////End of Help class //////////////////////////////////////////////////////


/**
* <h1> MovieStatistics Class </h1>
* This class has methods to give all the information about the movie 
*/



public class MovieStatistics extends Help{
	Movie original;
	List<String> sorted_list=new ArrayList<String>();
    List<Data> data = new ArrayList<Data>();
	/**<h1> Counts of characters </h1>
	* <p> This variable stores the counts of occurence of each character in the script</p>*/
	protected List<Integer> charcounts;
	/**<h1> Genders of characters </h1>
	* <p> This variable stores the gender of each character in the script</p>*/
	protected List<String> chargenders;	
	private void data_help(){
		List<Integer> counter = new ArrayList<Integer>();
		List<String> gender = new ArrayList<String>();
		
		for(int i=0;i<sorted_list.size();i++){
			for(int j=0;j<p.size();j++){
				if(sorted_list.get(i).equals(p.get(j))){
				    counter.add(charcounts.get(j));
				    gender.add(chargenders.get(j));
				    break;
				}
			}
		}
	   
		for(int i=0;i<sorted_list.size();i++){
			Data help = new Data();
			help.initialise(sorted_list.get(i),counter.get(i),gender.get(i));
			data.add(help);
		}
	}
	/**
	*<h1>Constructor</h1>
	*<p> This constructor requires a Movie object and returns a MovieStatistics object</p>
	*/
	public MovieStatistics(Movie given){
		super(given.movieFilename);
		original = given;
		charcounts = charCounts();
		chargenders = determineCharGender();
	}
	/**
	*<h1>Count of Words</h1>
	*<p> This method counts the number of words in the script and returns an int value</p>
	*/
	public int countScriptWords(){
		return original.getMovieWords().length;
	}
	/**
	*<h1>Count of Characters</h1>
	*<p> This method counts the number of characters in the script and returns an int value</p>
	*/
	public int countMovieCharacters(){
//		movie_characters();
		return p.size();
	}
	/**
	*<h1>Sorting Characters</h1>
	*<p> This method sorts the characters of the movie and then returns them as a list of Strings</p>
	*/
	public List<String> sortMovieCharacters(){
//		movie_characters();
		List<String> r = p;
		java.util.Collections.sort(r);
		sorted_list = r;
		return r;
	}

	int numberoftimes(String a, String[] b){
		/*for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == ' '){
				nospaces++;
			}
		}*/
		String[] modified = a.split(" ");
		int i = 0, count = 0;
		while(i < b.length){
			boolean ok = false;
			for(int j = 0;j< modified.length;j++){
				if(!(modified[j].equals(b[j+i]))){
					break;
				}
				if(j == modified.length - 1) ok = true;
			}
					
			if(ok){
				count++;
			}
			i++;
		}
		return count;
	}
	protected List<Integer> charCounts(){
		String[] source = original.getMovieWords();
//		for(int i = 0; i < source.length; i++) System.out.println(source[i]);
//		movie_characters();
		List<String> capitals = p;
//		System.out.println(p);
		List<String> smalls = q;
//		System.out.println(q);
		List<Integer> capitalCount = new ArrayList<Integer>();
		List<Integer> smallCount = new ArrayList<Integer>();
		List<Integer> allCount = new ArrayList<Integer>();
		for(int i = 0;i < capitals.size();i++) capitalCount.add(0);
		for(int i = 0;i < smalls.size();i++) smallCount.add(0);
		for(int i = 0;i<capitals.size();i++) capitalCount.set(i, numberoftimes(capitals.get(i), source));
		for(int i = 0; i < smalls.size(); i++) smallCount.set(i, numberoftimes(smalls.get(i), source));
		smallCount = final_count_for_gender(smallCount);
		for(int i = 0; i < smallCount.size();i++) allCount.add(capitalCount.get(i)+smallCount.get(i));
		return allCount;
	}
	/**
	*<h1>Count of individual characters</h1>
	*<p> This method prints the number of times each character occurs in the script</p>
	*/
	public void printCharCounts(){
		for(int i = 0; i < p.size(); i++) System.out.println(String.format("%-25s %-4s",p.get(i), charcounts.get(i)));
	}
		
	int numberoftimesl(String a, List<String> b){
		/*for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) == ' '){
				nospaces++;
			}
		}*/
		String[] modified = a.split(" ");
		int i = 0, count = 0;
		while(i < b.size()){
			boolean ok = false;
			for(int j = 0;j< modified.length;j++){
				if(!(modified[j].equals(b.get(j+i)))){
					break;
				}
				if(j == modified.length - 1) ok = true;
			}
					
			if(ok){
				count++;
			}
			i++;
		}
		return count;
	}

	int ispresent(String a, List<String> b){
		int i = 0;
		while(i < b.size()){
			if(b.get(i).contains(a)){
				return i;
			}
			i++;
		}
		return -1;
	}
	/**
	*<h1>Gender of Characters</h1>
	*<p> This method determines the gender of each character and them returns the list of string where each string is either "Male", "Female" or "Undetermined". The index of characters in this corresponds to the order in which characters are printed in printCharCounts() method</p>
	*/
	public List<String> determineCharGender(){
//		movie_characters();
		String[] source = original.getMovieWords();
//		for(int i = 0; i < source.length; i++ ) System.out.println(source[i]);
		List<String> r = q;
//		System.out.println(r);
		List<Integer> numbers = new ArrayList<Integer>();
		int i = 0;
		while(i < r.size()){
			numbers.add(0);
			i++;
		}
		i = 0;
		int present = -1;
		while(i < source.length){
			if(source[i].equals("he") || source[i].equals("He") || source[i].equals("his") || source[i].equals("His") || source[i].equals("him") || source[i].equals("Him") || source[i].equals("himself")){
				if(present != -1){
					numbers.set(present, numbers.get(present) + 1);
//					if(present == 3) System.out.println(source[i]);
				}
			}
			if(source[i].equals("she") || source[i].equals("She") || source[i].equals("her") || source[i].equals("Her") || source[i].equals("herself")){
//				System.out.println("Got it");
				if(present != -1){
					numbers.set(present, numbers.get(present)-1);
				}
			}
			if(ispresent(source[i], r) != -1){
				present = ispresent(source[i], r);
			}
			i++;
		}
//		System.out.println(numbers.size());
//		System.out.println(r.size());
		numbers = final_count_for_gender(numbers);
		List<String> g = new ArrayList<String>();
		for(int j = 0; j < numbers.size(); j++){
			if(numbers.get(j) > 0){
				g.add("Male");
			}
			if(numbers.get(j) < 0){
				g.add("Female");
			}
			if(numbers.get(j) == 0){
				g.add("Undetermined");
			}
		}
		return g;
	}
	/**
	*<h1>Count, gender of characters</h1>
	*<p> This method prints the number of times each character occurs in the script and also the gender of the character</p>
	*/
	public void printCharCountsWithGender(){
		
		for(int i = 0; i < p.size(); i++) System.out.println(String.format("%-25s %-4s %-10s", p.get(i), charcounts.get(i), chargenders.get(i)));

	}


	////////////////////////////villain code by the Heroic God //////////////////////////////

	private List<Integer> final_count_for_villain (List<Integer> n,List<String> w1){
		List<String> q1 = new ArrayList<String>();
		List<Integer> to_return = new ArrayList<Integer>();
//		System.out.println(n.size());
//		System.out.println(q.size());
		for(int i=0;i<p.size();i++) to_return.add(0);
		for(int i=0; i<w1.size();i++){
			q1.add(w1.get(i).toUpperCase());
		}
//		System.out.println(q.size());
//		System.out.println(n.size());
//		System.out.println("nothing");
		for(int i=0;i<w1.size();i++){
			for(int j=0;j<p.size();j++){
				if(q1.get(i).equals(p.get(j))){
					to_return.set(j,to_return.get(j)+n.get(i));
					break;
				}
			}
		}
		return to_return;

	}

	private int count_villain(String l,List<String> u){
		int count =0;
		//l = l.toLowerCase();
		for(int i=0;i<u.size();i++){
			int index=-1;
			index= l.indexOf(u.get(i));
			if (index != -1){
				count = 1 + count + count_villain(l.substring(index+u.get(i).length()-1,l.length()),u);
			}
		}
		return count;
	}

	/**
	*It finds the villain in the script.
	*@ return List<Integer> It contains counts of various characters which will be useful in detern=mining the villain
	*/

	protected List<Integer> villain_list(){
		List<String> searching= new ArrayList<String>();
		searching = p;
	    searching.addAll(q);
	//	System.out.println(searching);
		List<Integer> i1 = new ArrayList<Integer>();
		for(int i=0;i<searching.size();i++) i1.add(0);


		int line_no =0;
		int from = (9*no_lines_file)/10;
	//	System.out.println(from);


		try{
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			while (true) {
				String line = null;
				try{
					line=reader.readLine();
					line_no++;
				}
				catch(IOException p){
					System.out.println("File empty");
				}
				if (line == null){break;}
				if(line_no>from){
					for(int i=0;i<searching.size();i++){
						List<String> x = new ArrayList<String>();
						x.add(searching.get(i));
						i1.set(i,i1.get(i)+count_villain(line,x));
						//System.out.println(count_villain(line,x));
						//System.out.println(from);
					}

				}
			}


		}
	catch(FileNotFoundException a){
			System.out.println("File not found");
		}
		i1 = final_count_for_villain(i1,searching);
		return i1;
	}
//////////////////////////////villain code ends by God ////////////////////////////////////
}


