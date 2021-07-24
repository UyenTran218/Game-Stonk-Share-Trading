package unisa.dse.a1.students;

import unisa.dse.a1.interfaces.ListGeneric;

/**
 * @author simont
 * @param <T>
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric<T> head = null;
	private NodeGeneric<T> tail = null;
	private int size = 0;

	public DSEListGeneric() {
		head = tail = null;
		size = 0;	
	}
	public DSEListGeneric(NodeGeneric<T> head_) {
		if(head_ != null) {
			head = head_; //refer list's head to head_ node
			NodeGeneric<T> n = head;	
			n.prev = null;
			size = 1;
			
			if(n.next == null) {	
			tail = head;
			}
			
			while(n.next != null) {			
				size ++;
				n = n.next;
			}
			tail = n;
			}
		else {
			head = null;
			tail = null;
			size = 0;
			}
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<T> other) { 
		//check if other list is empty
				if (other.size == 0) {
					head = tail = null; //create empty list
					size = 0;
				}
				
				NodeGeneric<T> noderef = other.head;
				for (int i = 0; i < other.size; i ++) {
					add(other.get(i));
					noderef = noderef.next;
				}
	}
	public boolean add(int index, T obj) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
			}
		if (obj == null){
			return false;
			}
				
		NodeGeneric<T> newNode = new NodeGeneric<T>(null,null,obj);
		NodeGeneric<T> n = head;	
			if(index == size) {
				add(obj);
			}
			else {
			if(index == 0) {
				n.prev = newNode;
				newNode.next = n;
				head = newNode;
			}
			
			else {
				NodeGeneric<T> node = head;
				  for (int i=0; i<index; i++) {
				    node = node.next;
				  }
			
			newNode.next = node;
			newNode.prev = node.prev;
			node.prev.next = newNode;
			node.prev = newNode;}
			size++;		
			}
			return true;
	}
	public boolean contains(T obj) {
		return indexOf(obj) != -1;

	}
	public boolean remove(T obj) {
		//check if the parameter is null
				if(obj == null) {
					throw new NullPointerException();			
				}
				//check if the list is empty
				if(isEmpty()) {
					return false;
				}
				else
				//compare head node data to obj
				if(head.get().equals(obj)) {
					head = head.next;
					head.prev = null;
					size--;
					return true;
					}
				else {
					NodeGeneric<T> n = head;
					
					while(n.next!=null && !n.next.get().equals(obj)) {
						n = n.next;
					}
					if(n.next.next == null && n.next.get().equals(obj)) {
								tail = tail.prev;
								tail.next = null;
								size--;
								return true;}
					else {
						n.next = n.next.next;
						n.next.prev = n;
						size--;
						return true;
					}	
				}		
	}
	public T remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(size == 0) {
			return null;
		}
		if(index == 0) {
			NodeGeneric<T> h  = head;
			head = h.next;
			size--;
			return h.get();
		}
		if(size == 1) {
			tail = head = null;
			size = 0;
			return head.get();
		}
		
		if(index == size-1) {
			NodeGeneric<T> t = tail;
			tail = t.prev;
			tail.next = null;
			size--;
			return t.get();
		}

		NodeGeneric<T> ref = head;
		for(int i = 0; i < index; i++) {
			ref = ref.next;
		}
		ref.prev.next = ref.next;
		ref.next.prev = ref.prev;
		size --;
		
		return ref.get();
	}
	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		if (head == null) {
			return null;
		}
		if(index == 0)
		{
			return head.get();
		}
		if(index == size-1) {
			return tail.get();
		}
		NodeGeneric<T> node = head;
		  for (int i=0; i<index; i++) {
		    node = node.next;
		  }
		  return node.get();		
	
	}
	public int indexOf(T node) {
		if(node == null) {
			 throw new NullPointerException();
		 }	 
		 
		NodeGeneric<T> current = head;
		    int index = -1;

		    for(int i = 0; i < size; i++){
		        if(current.get().equals(node)){
		             index = i;
		             return index;
		        }
		        current = current.next; 
		    }
		    return index;
	}
	public boolean add(T obj) {
		if (obj == null){
			return false;}
				
		NodeGeneric<T> temp = new NodeGeneric<T>(null, null, obj);
		// add to empty list
		if (size == 0) {
		tail = head = temp;
		size = 1;
		return true;
		}
		//add to list that has one node
		if (size == 1){
		head.next = temp;
		temp.prev = head;
		temp.next = null;
		tail = temp; 
		size ++;
		return true;
		}
		
		tail.next = temp;
		temp.prev = tail;
		temp.next = null;
		tail = temp;
		size++;
		return true;
	}
	public boolean isEmpty() {
		return size() == 0;

	}
	public int size() {
		return size;
	}
	@Override
	public String toString() {
		NodeGeneric<T> nodeRef = head;
		StringBuilder result = new StringBuilder();
		  while (nodeRef != null) {
		    result.append(nodeRef.get());
		    if (nodeRef.next != null) {
		      result.append(" ");
		    }
		    nodeRef = nodeRef.next;
		  }
		  return result.toString();  
		
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(getClass() != obj.getClass()) {
			return false;
		}
		DSEListGeneric<T> ref = (DSEListGeneric<T>) obj;
		
		if(this.size != ref.size) {
			return false;
		}
		NodeGeneric<T> temp1 = this.head;
		NodeGeneric<T> temp2 = ref.head;
		while(temp1!= null && temp2 != null) {
			
			if(temp1.get() != temp2.get()) {
				return false;}
				
				temp1 = temp1.next;
				temp2 = temp2.next;
			}
		return true;
		
	}
	@Override
	public int hashCode() {
		return this.hashCode();
	}

	
}
