import java.util.NoSuchElementException;

//DON'T FORGET TO NULLIFY T(not privacy issue, but good practise, since it creates a sort of backdoor otherwise)
/**
 * This is the CellList class created for the Assignment 4.
 * CellList is a linked list which stores cellphones, and it has most of the common linked list methods
 * such as addToStart, replace, equald, etc. The copy constructor uses only one pointer to copy a list, and it uses a private
 * CellNode method to make some shallow copies of some nodes without causing a privacy issue.
 * CellNode is an inner class.
 * @author Morin-Laberge, William (ID #40097269), and Bouzidi, Camil (ID #40099611)
 * @version 4.0
 * COMP 249 
 * Assignment #4
 * April 8th 2019
 */
public class CellList {
	private CellNode head;
	//size is not static, since we're using an inner class
	private int size;
	
	/**
	 * This is the CellNode class created for the Assignment 4.
	 * CellNode is the class that presents the building block of the linked list, CelllList
	 * CellNode is an inner class.
	 * @author Morin-Laberge, William (ID #40097269), and Bouzidi, Camil (ID #40099611)
	 * @version 4.0
	 * COMP 249 
	 * Assignment #4
	 * April 8th 2019
	 */
	public class CellNode{
		private CellPhone phone;
		private CellNode node;
		
		/**
		 * Constructor
		 * Default constructor
		 * @return CellNode object
		 */
		public CellNode() {
			phone=null;
			node=null;
		}
		
		
		/**
		 * Constructor
		 * Parametrised constructor
		 * Parameterised constructor, this causes a privacy error, but is it necessary if we want to create meaningful nodes
		 * it is valid however, since when another class obtains an individual node, the next node must point to null
		 * only the private method innerClone uses this method with the argument node not null, which is desired
		 * privacy issue concerning the phone, which is needed to read from the file
		 * @return CellNode object
		 */
		public CellNode(CellPhone phone, CellNode node) {
			this.phone = phone;
			this.node = node;
			//size++;
		}
		/**
		 * Constructor
		 * copy constructor
		 * The cellphone is cloned, and a null pointer as a next node
		 * @return CellNode object
		 */
		public CellNode(CellNode c) {
			phone=c.phone.clone();
			//With the clone method, this might cause an infinite loop
			//The copy constructor calls the clone, but the clone calls copy constructor
			//node=c.node.clone();
			node = null;
		}
		
		/**
		 * Inner clone method
		 * No privacy issue here(the pointer is not cloned), since it is only used locally
		 * @return CellNode object
		 */
		private CellNode innerClone() {
			//No need to increase the size here, it is increased in the copy constructor
			return new CellNode(phone.clone(),node);
		}
		/**
		 * Standard clone method
		 * No privacy issue, since the copy constructor returns a null next node
		 * @return CellNode object
		 */
		public CellNode clone() {
			//No need to increase the size here, it is increased in the copy constructor
			return new CellNode(this);
		}
		/**
		 * Accessor
		 * No privacy issue, a cloned phone is returned
		 * @return CellPhone object
		 */
		public CellPhone getPhone() {
			return phone.clone();
		}
		/**
		 * Mutator
		 * No privacy issue, a cloned phone is used
		 * @param CellPhone object
		 */
		public void setPhone(CellPhone phone) {
			this.phone = phone.clone();
		}
		/**
		 * Accessor
		 * No privacy issue, the node is clone, the clone will have its clone node point to null
		 * @return CellNode object
		 */
		public CellNode getNode() {
			return node.clone();
		}
		/**
		 * Mutator
		 * This does not cause a privacy issue, since the nodes available to the other classes point to null
		 * @param CellNode object
		 */
		public void setNode(CellNode node) {
			this.node = node;
		}
	}
	
	//Back to CellList
	/**
	 * Constructor
	 * Default constructor
	 * @return CellList object
	 */
	public CellList() {
		size=0;
		head=null;
	}
	/**
	 * Constructor
	 * copy constructor
	 * This method uses a single pointer to perform a deep copy of a CellList, it uses a private method
	 * with potential privacy leaks, since during the copying, the last two nodes will point to the same next node.
	 * After the copying, they both point to null, so there is no privacy issue overall.
	 * @return CellList object
	 * @param CellList object
	 */
	public CellList(CellList c) {
		size=0;
		head=null;
		if (c.size!=0) {
			size=c.size;	//The size must be set, since the increment instruction in the node class won't work, since we are constructing
			CellNode copy= c.head.innerClone();
			//only the head, which points to the first copy pointer is returned
			//the copy pointer then moves along the list to copy everything
			head = copy;
			while(copy.node != null) {
				copy.node = copy.node.innerClone();
				copy = copy.node;
			}
			//Getting rid of the pointer
			copy=null;
		}
	}
	/**
	 * This adds the passed Cellphone to the start of the list by creating a node and shifting the head.
	 * @param CellPhone object
	 * @return void
	 */
	public void addToStart(CellPhone c) {
		if (c==null)
			return;
		if (!contains(c.getSerialNum())) {
			head = new CellNode(c,head);
			size++;
		} else {
			System.out.println("#A duplicate was spotted!# The phone with serial number: "+c.getSerialNum()+" will only be recorded once.");
		}
	}
	/**
	 * Deletes the first node by shifting the head pointer to the next node in the list
	 * @return void
	 */
	public void deleteFromStart() {
		if (head!=null) {
			//if there's only one node, that node is deleted
			size--;
			if (head.node==null) {
				head=null;
				return;
			}
			head=head.node;
		}
		//if the head points to null, nothing happens
	}
	/**
	 * Inserts a Cellphone in the list at the specified index if it is not there already
	 * @return void
	 * @param index
	 * @param CellPhone object
	 */
	public void insertAtIndex(int index, CellPhone c) {
		if (index<0 || index> size-1) 
			throw new NoSuchElementException();
		if (index==0)
			addToStart(c);
		else {
			CellNode t = head;
			//Getting to the position right before the index
			for (int i = 0; i <index-1; i++) {
				t = t.node;
			}
			//insert the node, if it doesn't exist already
			if (!contains(c.getSerialNum())) {
				t.node = new CellNode(c, t.node);
				size++; //size is NOT increased in constructor
			}
			t=null;
		}
	}
	/**
	 * Deletes a node at the specified index by taking the next node pointer of the node before the deleted node
	 * and making it point to the node the deleted node points to.
	 * @return void
	 * @param index
	 */
	public void deleteAtIndex(int index) {
		if (index<0 || index> size-1) 
			throw new NoSuchElementException();
		if (index==0)
			deleteFromStart(); //deleteFromStart reduces index
		else {
			size--;
			CellNode t = head;
			//Getting to the position right before the index
			for (int i = 0; i <index-1; i++) {
				t = t.node;
			}
			//t.node points to the node after t, so by making t.node point to t.node.node, we are skipping over a node, which becomes an orphan object
			t.node = t.node.node;
			t=null;
		}
	}
	/**
	 * Replaces a node at an index by deleting it and inserting the desired node
	 * @return void
	 * @param index
	 * @param CellPhone object
	 */
	public void replaceAtIndex(int index, CellPhone c) {
		deleteAtIndex(index);
		insertAtIndex(index, c);
	}
	/**
	 * This method parses the list to find the phone with the given serial number
	 * @return boolean
	 * @param x, representing a serial number
	 */
	public boolean contains(long x) {
		CellNode t = head;
		while(t!=null) {
			if (t.phone.getSerialNum()==x) {
				t=null;
				return true;
			}
			t = t.node;
		}
		return false;
	}
	/**
	 * This method parses the list to find the phone with the given serial number, and returns a copy of the node containing it
	 * @return CellNode object
	 * @param x, representing a serial number
	 */
	public CellNode find(long x) {
		//
		int counter=0;
		CellNode t = head;
		while(t!=null) {
			if (t.phone.getSerialNum()==x) {
				System.out.println("The phone was found after "+counter+" tries.");
				return t.clone();
			}
			t = t.node;
			counter++;
		}
		//Here t is null, so 
		System.out.println("The phone is not in the list!");
		return t;
		
	}
	/**
	 * This method parses the list to print it
	 * @return void
	 */
	public void showContents() {
		CellNode t = head;
		System.out.println("This CellList has a size of " + size);
		for(int i=1; t!=null; i++) {
			System.out.print("["+t.phone+"] ---> ");
			if (i%3==0) 
				System.out.println(); //to skip a line once every three nodes
			t = t.node;
		}
		System.out.println("X");
		t=null;
	}
	/**
	 * This method parses two lists, comparing nodes to determine if they are equal or not.
	 * two lists are equal if they have the same cellphones at the *same positions*, but with different serial numbers.
	 * @return boolean
	 * @param x, representing a serial number
	 */
	public boolean equals(Object o) {
		if (o==null)
			return false;
		if (getClass()!=o.getClass())
			return false;
		CellList l = (CellList)o;
		if (l.size!=this.size)
			return false;
		//Here we know we have two lists of the same size
		CellNode t1 = head;
		CellNode t2 = l.head;
		while (t1!=null) {
			if (!t1.phone.equals(t2.phone)) {
				System.out.println("The phones "+ t1.phone +"~~~~~~"+ t2.phone+" are different");
				t1=t2=null;
				return false;
			}
			t1 = t1.node;
			t2 = t2.node;
		}
		//We've parsed the entire lengths without finding a single mismatch
		t1=t2=null;
		return true;
	}
	
	
}
