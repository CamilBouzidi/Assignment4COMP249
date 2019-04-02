import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Assignment4Question1 {
	//I think I should create a method that check that the next word is a word, not a \!, \., etc.
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		Scanner sc = null;
		PrintWriter pw = null;
		String nameIn=null;
		System.out.println("Welcome to the Sub-dictionary Creator!"
				+ "\n Please enter the name of the .txt file you would like to go through: ");
		nameIn = kb.next();
		ArrayList<String> wordList = new ArrayList<String>();
		ArrayList<String> illegal = new ArrayList<String>();
		boolean canWrite = true;
		illegal.add(".");
		illegal.add(",");
		illegal.add("!");
		illegal.add("?");
		illegal.add(";");
		illegal.add(":");
		illegal.add("=");
		
		try {
			sc = new Scanner(new FileInputStream(nameIn+".txt"));
			pw = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Program will terminate.");
			sc.close();
			pw.close();
			System.exit(0);
		} finally {
			while (sc.hasNext()) {
				String word = sc.next();
				word = word.toUpperCase();
				for (String p : illegal) {
					if (word.contains(p)) {
					System.out.println("Error: "+p+" found in "+word);
					word = word.substring(0, word.length()-1);
					System.out.println("The word is now: "+word);
					}
				}
				if (word.contains("’")) {
					word = word.substring(0,word.indexOf("’"));
				}
				if (!wordList.contains(word)) {
					if ((word.length()==1)&&(!word.equals("I"))&&(!word.equals("A")));//Do Nothing
						//canWrite=false;
					/*for (int i=0; i<10; i++) {
						if (word.contains(i+"")) {
							canWrite=false;
							break;
						}
					}*/
					else
						wordList.add(word);
				}
			}
			wordList.sort(null);
			wordList.remove(0);
			wordList.trimToSize();
			char begin = 'a';
			for (String a: wordList) {
				//System.out.println(a.charAt(0));
				//String delim = begin+"";
				//if (a.charAt(0)==begin) {
					//pw.println(delim.toUpperCase()+"\n==");
					//begin++;
				//}
				pw.print(a+"\n");
				System.out.println(a);
			}
			pw.close();
		}
	}

}
