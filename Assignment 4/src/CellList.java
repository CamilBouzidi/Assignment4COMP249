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
		if (index<0 || index< size-1) 
			throw new NoSuchElementException();
		if (index==0)
			addToStart(c);
		else {
			CellNode t = head;
			//Getting to the position right before the index
			for (int i = 0; i <index-1; i++) {
				t = t.node;
			}
			//insert the node
			t.node = new CellNode(c, t.node);
		}
	}
	
	public void deleteAtIndex(int index) {
		if (index<0 || index< size-1) 
			throw new NoSuchElementException();
		if (index==0)
			deleteFromStart(); //deleteFromStart reduces index
		else {
			size--;
			CellNode t = head;
			CellNode s;
			//Getting to the position right before the index
			for (int i = 0; i <index-1; i++) {
				t = t.node;
			}
			//s points to the node after t, so by making t.node point to s.node, we are skipping over a node, which becomes an orphan object
			s = t.node;
			t.node = s.node;
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
			if (t.phone.getSerialNum()==x)
				return t;
			t = t.node;
			counter++;
		}
		//Here t is null, so 
		return t;
		
	}
	
	public void showContents() {
		CellNode t = head;
		for(int i=1; t!=null; i++) {
			System.out.print("["+t.phone+"] ---> ");
			if (i%3==0) 
				System.out.println(); //to skip a line once every three nodes
			t = t.node;
		}
		System.out.println("X");
	}
	
	
}
