package unisa.dse.a1.students;

import unisa.dse.a1.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head, tail = null;
	private int size = 0;


	public DSEList() {
		head = tail = null;
		size = 0;
	}
	public DSEList(Node head_) {
		//check if head_ node is not null
		if(head_ != null) {
			head_.prev = null;
			head = head_; 
			size = 1;
			
			if(head_.next == null) {	
			tail = head;
			}
			
			while(head_.next != null) {			
				size ++;
				head_ = head_.next;
			}
			tail = head_;
			
		}
		//if head_ is null, we have an empty list
		else {
			head = tail = null;
			size = 0;
			}
		
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		//check if other list is empty
		if (other.size == 0) {
			head = tail = null; //create empty list
			size = 0;
		}
		//add new nodes which has data of other list's nodes into the new list
		else {
		Node noderef = other.head;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		for (int i = 0; i < other.size; i ++) {
			add(other.get(i));
			noderef = noderef.next;		
		}
		}
		
	}

	//remove the String at the parameter's index
	public String remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		//remove at the list beginning
		if(index == 0) {
			Node h  = head;
			//set new head ater removing old head if list size > 1
			if(head.next!=null) {
			head = head.next;
			head.prev = null;
			}
			size--;
			return h.getString();
		}
		else
			//remove at the end of list
			if(index == size-1) {
				Node t = tail;
				//set new tail 
				tail = tail.prev;
				//unlink old tail
				tail.next = null;
				size--;
				return t.getString();
				}
			else
			{
				Node ref = head;
				//loop through list to find index
				for(int i = 0; i < index; i++) {
					ref = ref.next;
					}
				
				ref.prev.next = ref.next;
				ref.next.prev = ref.prev;
				size --;
		
				return ref.getString();}

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		//check if obj is null
		if(obj.equals(null)) {
			 return -1;
			 }	 
		    Node current = head; 
		    int index = -1;

		    for(int i = 0; i < size; i++){
		        if(current.getString().equals(obj)){
		             index = i;
		             }
		        current = current.next; 
		        }
		    return index;
	}
	
	//returns String at parameter's index
	public String get(int index) {
		//check if index is out of bound
		if (index < 0 || index >= size) {
			return null;
		}
		
		//get head data
		if(index == 0)
		{
			return head.getString();
		}
		//get tail data
		if(index == size-1) 
		{
			return tail.getString();
		}
		//get data of a node in the middle of list
		Node node = head;
		  for (int i=0; i<index; i++) {
		    node = node.next;
		  }
		  return node.getString();		
	
	}

	//checks if there is a list
	public boolean isEmpty() {
		return size() == 0;
	}

	//return the size of the list
	public int size() {
		return size;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		Node nodeRef = head;
		  StringBuilder result = new StringBuilder();
		  //loop through list and print out node data as String type
		  while (nodeRef != null) {
		    result.append(nodeRef.getString());
		    //print out " " between each String but not after the last String
		    if (nodeRef.next != null) {
		      result.append(" ");
		    }
		    nodeRef = nodeRef.next;
		  }
		  return result.toString();   
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		//check null string
		if (obj == null){
			return false;
			}
		
		
		Node temp = new Node(null, null, obj);
		// add to empty list
		if (size == 0) {
		head = temp;
		tail = head;
		
		}
		else {
		//set new tail by creating links to temp node
		tail.next = temp;
		temp.prev = tail;
		tail = temp;	
		}
		
		size++;
		return true;
		
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
		//check if index is out of bound
		if (index < 0 || index > size ) {
			throw new IndexOutOfBoundsException();
			}
		//check if obj is null
		if (obj == null){
			return false;}
		//create new node with obj data		
			Node newNode = new Node(null,null,obj);
			//refer to head node
			Node n = head;	
			
			if(index == size) {
				//add new node to the end of list
				add(obj); 
			}
			
			else {
			if(index == 0) {
				//add new node to the list beginning
				n.prev = newNode;
				newNode.next = n;
				head = newNode; 
			}
			
			else {
			//loop through list to find the position to add new node	
				  for (int i=0; i<index; i++) {
				    n = n.next;
				  }
			
			newNode.next = n;
			newNode.prev = n.prev;
			n.prev.next = newNode;
			n.prev = newNode;
			}
			size++;		
			}
			return true;
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		return indexOf(obj) != -1;
	}

	//removes the parameter's String from the list
	public boolean remove(String obj) {
		//check if the parameter is null
		if(obj == null) {
			throw new NullPointerException();			
		}
		if(contains(obj) == false) {
			return false;
		}
		//check if the list is empty
		if(size == 0) {
			return false;
		}
		else
		//compare head node data to obj
		if(head.getString().equals(obj)) {
				head = head.next;
				head.prev = null;
				size--;
				return true;
			}	
		else
		{
		Node n = head;
		//loop through list to find obj string
		while(n.next!=null && !n.next.getString().equals(obj)) {
			n = n.next;
		}
		//if tail's data equal obj we remove the current tail and set new tail for the list
		if(n.next.next == null && n.next.getString().equals(obj)) {
					tail = tail.prev;
					tail.next = null;
					size--;
					return true;}
		else {
			//remove a middle node that got obj data
			n.next = n.next.next;
			n.next.prev = n;
			size--;
			return true;
		}
			
		}
	}
	
	@Override
	public int hashCode() {
		return this.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if(this.toString().equals(other.toString())) {
		return true;
		}
		else
		return false;
	}
	
}
