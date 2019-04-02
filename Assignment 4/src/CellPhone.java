import java.util.Scanner;

public class CellPhone {
	private long serialNum;
	private String brand; //Always one word!
	private int year;
	private double price;
	
	public CellPhone(long serialNum, String brand, int year, double price) {
		super();
		this.serialNum = serialNum;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	public CellPhone(long serialNum, CellPhone cell) {
		this.serialNum=serialNum;
		year = cell.year;
		price = cell.price;
		brand = cell.brand;
	}
	
	public CellPhone clone() {
		long input=0;
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter a new serial number:");
		input = kb.nextLong();
		kb.close();
		return new CellPhone(input, this);
	}

	@Override
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
		if (brand.equals(c.brand)&&Double.doubleToLongBits(price)==Double.doubleToLongBits(c.price)&&year==c.year)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return serialNum + ": " + brand + " " + price + "$ " + year;
	}

	public long getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(long serialNum) {
		this.serialNum = serialNum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
