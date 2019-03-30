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
		try {
			sc = new Scanner(new FileInputStream(nameIn+".txt"));
			pw = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Program will terminate.");
			System.exit(0);
		} finally {
			while (sc.hasNext()) {
				String word = sc.next();
			}
		}
	}

}
