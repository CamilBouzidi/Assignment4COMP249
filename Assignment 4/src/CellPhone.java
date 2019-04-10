/**
 * This is the CellPhone class created for Assignment 4.
 * It has 4 attributes, along with getters, setters, parameterised constructor, copy constructor,
 * as well as appropriate equals, toString and clone methods. 
 * @author Morin-Laberge, William (ID #40097269), and Bouzidi, Camil (ID #40099611)
 * @version 1.5
 * COMP 249 
 * Assignment #4
 * April 7 2019
 */
public class CellPhone {
	private long serialNum;
	private String brand; //Always one word!
	private int year;
	private double price;
	//serialNum, use a static var instead(don't ask the user)
	private static int counter=0;
	
	/**
	 * Parameterised Constructor. Creates a CellPhone with all attributes set by the user.
	 * @param serialNum: wanted serial number.
	 * @param brand: brand of the phone.
	 * @param year: year of creation of the phone.
	 * @param price: price of the phone
	 */
	public CellPhone(long serialNum, String brand, int year, double price) {
		this.serialNum = serialNum;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	/**
	 * Copy Constructor. Creates a CellPhone with the same attributes as the passed CellPhone, except for the serial number, which is set by the author.
	 * @param serialNum: wanted serial number.
	 * @param cell: CellPhone to copy.
	 */
	public CellPhone(long serialNum, CellPhone cell) {
		this.serialNum=serialNum;
		year = cell.year;
		price = cell.price;
		brand = cell.brand;
	}
	
	/**
	 * Utilizes the Copy Constructor. Gives a different serial number on the new phone, based on the class' counter.
	 * @return: CellPhone: the new, copied CellPhone.
	 */
	public CellPhone clone() {
		//The part commented below is not necessary
//		long input=0;
//		Scanner kb = new Scanner(System.in);
//		System.out.println("Please enter a new serial number:");
//		input = kb.nextLong();
//		kb.close();
		return new CellPhone(counter++, this);
	}

	/**
	 * Proper equals method. Two CellPhones are equal if they have the same attributes, except for the serial number. If it is equal, then the CellPhones shouldn't be equal.
	 * @param obj: Object to be verified by the calling CellPhone.
	 * @return boolean: true if the phone are equal, false if they are not.
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass()!=obj.getClass())
			return false;
		CellPhone c = (CellPhone)obj;
		if (serialNum==c.serialNum) {
			System.out.println("Error! Two cellphones have the same ID!");
			return false;
		}
		//The ID should NOT be the same
		if (brand.equals(c.brand)&&Double.doubleToLongBits(price)==Double.doubleToLongBits(c.price)&&year==c.year) {
			return true;
		}
		return false;
	}

	/**
	 * Proper toString method, containing all of the CellPhone's attributes.
	 * @return String: a description of the CellPhone's attributes.
	 */
	public String toString() {
		return serialNum + ": " + brand + " " + price + "$ " + year;
	}
	
	/**
	 * Getter for the serial number.
	 * @return long: the serial number.
	 */
	public long getSerialNum() {
		return serialNum;
	}
	
	/**
	 * Setter for the serial number.
	 * @param serialNum: the desired serial number for the CellPhone. 
	 * @return void
	 */
	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}
	
	/**
	 * Getter for the brand. 
	 * @return String: the brand of the CellPhone.
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * Setter for the brand.
	 * @param brand: the desired, new brand for the CellPhone.
	 * @return void
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/**
	 * Getter for the year.
	 * @return int: the year of the CellPhone.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Setter for the year.
	 * @param year:
	 * @return void
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * Getter for the price
	 * @return double: returns the price of the CellPhone.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter for the price.
	 * @param price: the new, desired price for the CellPhone.
	 * @return void
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
