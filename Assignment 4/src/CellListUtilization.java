import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
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
		System.out.println("Showing the contents of list2 (Copy of non-empty list1): ...");
		list2.showContents();
		
		System.out.println("Deleting the phone at index 3 and re-showing the contents of list2: ...");
		try {
			list2.deleteAtIndex(3);
			list2.showContents();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.out.println("That index doesn't exist! Program will exit.");
			sc.close();
			System.exit(0);
		}
		
		System.out.println("Adding a cellphone at the start of list2: ...");
		list2.addToStart(new CellPhone(70, "Google", 2019, 1000.00));
		list2.showContents();
		
		System.out.println("Deleting a cellphone at the start of list2: ...");
		list2.deleteFromStart();
		list2.showContents();
		
		System.out.println("Adding a phone at index 10 of list2: ...");
		System.out.println("the phone is the following: ");
		CellPhone goo = new CellPhone(71, "Google", 2019, 1000.00);
		try {
			list2.insertAtIndex(10, goo);
			list2.showContents();
		} catch (NoSuchElementException e2) {
			System.out.println("That index doesn't exist! Will exit the program.");
			sc.close();
			System.exit(0);
		}
		
		System.out.println("Creating new phone Apple 977.27$ 2016 with Serial Num 11: ...");
		CellPhone app = new CellPhone(72, "Apple", 2016, 977.27);
		System.out.println("Checking if it is in list2:");
		if (list2.contains(app.getSerialNum())) {
			System.out.println("There's already a phone like this in the list.");
		} else {
			System.out.println("There is not a phone like this in list2.");
		}
		
		System.out.println("Replacing the phone at index 19 with the previous Apple phone: ");
		try {
			list2.replaceAtIndex(19, app);
			list2.showContents();
		} catch (NoSuchElementException e3) {
			System.out.println("This index doesn't exist! The program will exit.");
			sc.close();
			System.exit(0);
		}
		
		System.out.println("Checking if a cellphone with ID 10000 exists in list2: ");
		list2.find(10000);
		System.out.println("Checking if a cellphone with ID 12 exists in list2: ");
		list2.find(12);
		System.out.println("Checking if a cellphone with ID -5 exists in list2: ");
		list2.find(-5);
		
		System.out.println("CHECKING THE SIZE OF LIST1: ");
		list1.showContents();
		System.out.println("Chekcking if list1 and list2 have the same contents: ");
		if (list1.equals(list2))
			System.out.println("list1 and list2 have the same contents.");
		else
			System.out.println("list1 and list2 don't have the same contents.");
		
		System.out.println("------------------NOW TESTING EMPTY LISTS----------------");
		CellList eList = new CellList();
		
		System.out.println("Showing the contents of elist (empty list): ");
		eList.showContents();
		
		System.out.println("Adding a cellphone at the start of elist: ...");
		eList.addToStart(app);
		System.out.println("Showing the contents of elist after adding a phone at the start: ");
		eList.showContents();
		
		System.out.println("Deleting the cellphone a the start of elist: ...");
		eList.deleteFromStart();
		System.out.println("Showing the contents of elist after deleting the phone at the start: ");
		eList.showContents();
		
		System.out.println("Looking for a phone with ID 78 in elist: ");
		if (eList.contains(78))
			System.out.println("The phone with ID 78 is in elist.");
		else
			System.out.println("The phone with ID 78 is not in elist.");
		
		System.out.println("Looking for the index where ID 78 appears: ");
		eList.find(78);
		
		System.out.println("Removing a cellphone from eList at a non-existing index:...");
		try {
			eList.deleteAtIndex(3);
			eList.showContents();
		} catch (NoSuchElementException e) {
			System.out.println("That index doesn't exist! Program will exit.");
			sc.close();
			System.exit(0);
		}
	
		sc.close();
	}

}
