import java.util.NoSuchElementException;

//DON'T FORGET TO NULLIFY T(not privacy issue, but good practise, since it creates a sort of backdoor otherwise)
public class CellList {
	private CellNode head;
	//size is not static, since we're using an inner class
	private int size;
	
	//might need some more work, no shallow copies are allowed
	public class CellNode{
		private CellPhone phone;
		private CellNode node;
		
		public CellNode() {
			phone=null;
			node=null;
			size++;
		}
		
		//Parameterised constructor, this causes a privacy error, but is it necessary if we want to create meaningful nodes
		//it is valid however, since when another class obtains an individual node, the next node must point to null
		//only the private method innerClone uses this method with the argument node not null, which is desired
		//privacy issue concerning the phone, which is needed to read from the file
		public CellNode(CellPhone phone, CellNode node) {
			this.phone = phone;
			this.node = node;
			size++;
		}
		
		public CellNode(CellNode c) {
			phone=c.phone.clone();
			//With the clone method, this might cause an infinite loop
			//The copy constructor calls the clone, but the clone calls copy constructor
			//node=c.node.clone();
			node = null;
			size++;
		}
		
		//No privacy issue here, since it is only used locally
		private CellNode innerClone() {
			//No need to increase the size here, it is increased in the copy constructor
			return new CellNode(phone.clone(),node);
		}
		
		public CellNode clone() {
			//No need to increase the size here, it is increased in the copy constructor
			return new CellNode(this);
		}

		public CellPhone getPhone() {
			return phone.clone();
		}

		public void setPhone(CellPhone phone) {
			this.phone = phone.clone();
		}

		public CellNode getNode() {
			return node.clone();
		}
		//This does not cause a privacy issue, since the nodes available to the other classes point to null
		public void setNode(CellNode node) {
			this.node = node;
		}
	}
	
	//Back to CellList
	
	public CellList() {
		size=0;
		head=null;
	}
	
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
	
	public void addToStart(CellPhone c) {
		if (c==null)
			return;
		if (!contains(c.getSerialNum())) {
			head = new CellNode(c,head);
		} else {
			System.out.println("#A duplicate was spotted!# The phone with serial number: "+c.getSerialNum()+" will only be recorded once.");
		}
	}
	
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
				//size++; size is increased in constructor
			}
		}
	}
	
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
		}
	}
	
	public void replaceAtIndex(int index, CellPhone c) {
		deleteAtIndex(index);
		insertAtIndex(index, c);
	}
	
	public boolean contains(long x) {
		CellNode t = head;
		while(t!=null) {
			if (t.phone.getSerialNum()==x)
				return true;
			t = t.node;
		}
		return false;
	}
	
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
	}
	
	//two lists are equal if they have the same cellphones at the *same positions*, but with different serial numbers.
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
			if (t1.phone.equals(t2.phone))
				return false;
			t1 = t1.node;
			t2 = t2.node;
		}
		//We've parsed the entire lengths without finding a single mismatch
		return true;
	}
	
	
}
