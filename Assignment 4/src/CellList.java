import java.util.NoSuchElementException;

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
		//Parameterized constructor
		public CellNode(CellPhone phone, CellNode node) {
			this.phone = phone;
			this.node = node;
			size++;
		}
		
		//This method causes a privacy issue, which is better, however, than an infinite loop
		public CellNode(CellNode c) {
			phone=c.phone.clone();
			//With the clone method, this might cause an infinite loop
			//The copy constructor calls the clone, but the clone calls copy constructor
			//node=c.node.clone();
			node = c.node;
			size++;
		}
		
		public CellNode clone() {
			//No need to increase the size here, it is increased in the copy constructor
			return new CellNode(this);
		}

		public CellPhone getPhone() {
			return phone;
		}

		public void setPhone(CellPhone phone) {
			this.phone = phone;
		}

		public CellNode getNode() {
			return node;
		}

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
		if (c.size!=0) {
			//size=c.size;	No need to set the size, it will get incremented as the nodes are cloned
			size=0;
			CellNode copy= c.head.clone();
			//only the head, which points to the first copy pointer is returned
			//the copy pointer then moves along the list to copy everything
			head = copy;
			while(copy.node != null) {
				copy.node = copy.node.clone();
				copy = copy.node;
			}
		}
	}
	
	public void addToStart(CellPhone c) {
		head = new CellNode(c,head);
	}
	
	public void insertAtIndex(int x, CellPhone c) {
		if (x<0 || x< size-1) 
			throw new NoSuchElementException();
		if (x==0)
			addToStart(c);
		else {
			CellNode t = head;
			//Getting to the position right before the index
			for (int i = 0; i <x-1; i++) {
				t = t.node;
			}
			//insert the node
			t.node = new CellNode(c, t.node);
		}
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
	
	public void showContents() {
		CellNode t = head;
		for(int i=1; t!=null; i++) {
			System.out.print("["+t.phone+"] ---> ");
			if (i%3==0) 
				System.out.println();
			t = t.node;
		}
		System.out.println("X");
	}
	
	
}
