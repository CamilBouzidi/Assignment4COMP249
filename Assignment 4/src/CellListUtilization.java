import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CellListUtilization {

	public static void main(String[] args) {
		CellList list1 = new CellList();
		CellList list2 = new CellList();
		
		Scanner sc =null;
		try {
			sc = new Scanner(new FileInputStream("Cell_Info.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Check the folder, the file is not there, will terminate now.");
			System.exit(0);
		}
		//reading
		long serial;
		String brand;
		double price;
		int year;
		while(sc.hasNext()) {
			serial = sc.nextLong();
			brand = sc.next();
			price = sc.nextDouble();
			year = sc.nextInt();
			//For debugging: System.out.println(serial+"-" + brand+"-" + price+"-" + year);
			//Here, if a pointer to the cellphone sent to addToStart was kept, one could modify the cellphone in the list through it
			//this is necessary, otherwise we can't read from the file, as we would create a clone with a different serial number
			list1.addToStart(new CellPhone(serial,brand,year,price));
		}
		//list1.showContents();
		list2 = new CellList(list1);
		list2.showContents();
		list2.deleteAtIndex(3);
		list2.showContents();
	}

}
