
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

		public CellNode(CellPhone phone, CellNode node) {
			this.phone = phone.clone();
			this.node = node.clone();
			size++;
		}
		
		public CellNode(CellNode c) {
			phone=c.phone;
			node=c.node;
			size++;
		}
		
		public CellNode clone() {
			size++;
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
	
	public CellList() {
		size=0;
		head=null;
	}
	
	public CellList(CellList c) {
		size=c.size;
		//should use head in loop to copy all nodes
	}
}
