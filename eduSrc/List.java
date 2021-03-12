package edu;

import java.util.Iterator;

import static java.lang.System.*;

public class List implements ListInterface, Iterable<ListNode> {

	
    private ListNode inode; //iterator node
    private int size;

    public ListNode head;
    public ListNode tail;

    /**
     * List
     */
    public List() {
        clear();
    }

    //constructor with Object
	public List(Object object) {
        add(object);
    }

    //check emptyness
	public boolean isEmpty() {
        return (head == null);
    }

    //get size
	public int getSize() {
        return size;
    }

    //wipe
	public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    //get head
	public Object getHead() {
        return head;
    }

    //get tail
	public Object getTail() {
        return tail;
    }

    //find and return an object as a ListNode
	public ListNode search(Object object) {

        Iterator<ListNode> i = this.iterator();
        ListNode inode;

        while ((inode = i.next()) != null) {
			//System.out.println(inode);
            if (inode.getObject().toString().equals(object.toString())) {
                return inode;
            }
        }
        return null;
    }
    
    //find and return an object as a ListNode
	public ListNode searchBack(Object object) {

        Iterator<ListNode> i = this.iteratorBack();
        ListNode inode;

        while ((inode = i.next()) != null) {
			//System.out.println(inode); //<- VER RECORRIDO EN REVERSA
            if (inode.getObject().toString().equals(object.toString())) {
                return inode;
            }
        }
        return null;
    }

    //add object
	public boolean add(Object object) {
        return insertTail(object);
    }

    //insert object in node location
    public boolean insert(ListNode node, Object object) {
        try{

			//tail node
            if (node.next == null){
                add(object);
            } 
			//head or middle node
			else{
                ListNode 
                middleNode = new ListNode(object), //create new node
                tailNode = node.next;

                middleNode.next = tailNode; 
                middleNode.before = node;

                node.next = middleNode; //Old -> New -> Tail
                tailNode.before = middleNode;
            }
            return true;
        }
		catch (Exception e){
            return false;
        }
    }

    //insert object in object location
	public boolean insert(Object ob, Object object) {
        try{
            if (ob != null){

				//search node
                ListNode node = this.search(ob);

                if (node != null){ //found
					return insert(node, object);
                }
				else{ //not found
                    return false;
                }
            }
			else{
                return false;
            }
        }
		catch (Exception e){
            return false;
        }
    }

	//insert new head
    public boolean insertHead(Object object) {
        try{
            if (isEmpty()){
                head = new ListNode(object);
                tail = head;
            } 
			else{
                ListNode _node = new ListNode(object, head);
                head.before = _node;
                head = _node;
            }
            this.size++;
            return true;
        }
		catch (Exception e){
            return false;
        }
    }

    //insert to tail
    public boolean insertTail(Object object) {
        try{
            if (isEmpty()){
                head = new ListNode(object);
                tail = head;
            }
			else{

                tail.next = new ListNode(object, null);
                ListNode _node = tail.next;

                _node.before = tail;
                tail = tail.next;
            }
            this.size++;
            return true;
        }
		catch (Exception e){
            return false;
        }
    }

    //remove node
    public boolean remove(ListNode node) {
		try{
			ListNode 
			remNode = this.search(node.getObject()), //verify its part of the list
			preNode = this.getBeforeTo(remNode.getObject());

			//exists?
			if (remNode != null){
				if (preNode != null){ //middle or tail
					if (remNode.next != null){ //middle
						preNode.next = remNode.next;
					}
					else{ //tail
						preNode.next = null;
					}
					size--;
				}
				else{ //head
					if (remNode.next != null){
						head = remNode.next;
					}
					else{ //the only element in the list
						head = null;
					}
					size--;
				}
			}

			return false;
		}
		catch (Exception e){
			return false;
		}
    }

    //remove node from object 
    public boolean remove(Object object) {
		try{
			ListNode node = this.search(object);

			if (node != null){
				remove(node);
			}
			return false;
		}
		catch (Exception e){
			return false;
		}
    }

    //Check its in the list 
    public boolean contains(Object object) {
		if (this.search(object) != null)	
			return true;
		else
			return false;
    }

	//Return an array version
    public Object[] toArray() {
		try{
			Object arr[] = new Object[size];
			int _ind = 0;
	
			//Iterar
        	Iterator<ListNode> i = this.iterator();
    	    ListNode inode;
	
	        while ((inode = i.next()) != null) {
				arr[_ind] = inode.getObject();
				_ind++;
    	    }
	        return arr;
		}

		catch (Exception e){
			return null;
		}
    }

    //add object and return array
    public Object[] toArray(Object[] object) {

        //add new object
        add(object);

        //return array version
        return toArray();
    }

    
    public Object getBeforeTo() {return null;}

    //find the one before the node 
    public ListNode getBeforeTo(ListNode node) {
		if (node != null){
			return getBeforeTo(node.getObject());
		}
		return null;
    }

	//find the one before the object
	public ListNode getBeforeTo(Object object){
        Iterator<ListNode> i = this.iterator();
        ListNode inode, nodeNext;

        while ((inode = i.next()) != null) {
			if (inode.next != null){
				nodeNext = inode.next;
				if (nodeNext.getObject().toString().equals(object.toString())){
					return inode;
				}
			}
        }
        return null;
	}

    
    public Object getNextTo() {return null;}

	//find the one next the node
    public Object getNextTo(ListNode node) {
        if (node != null){
			return(node.next);
		}
		return null;
    }

    //Create sublist
    public List subList(ListNode from, ListNode to) {
		try{
			List lsSub = new List();

			if (from != null && to != null){
	        	Iterator<ListNode> i = this.iterator(from);
    		    ListNode inode, newNode;
				boolean start = false;

		        while ((inode = i.next()) != null) {

					//start
					if (inode != null){

						System.out.println("\nAdd this: ");
						System.out.println(inode);
						if (inode.isEquals(to)){ //end
							lsSub.add(inode.getObject());
							break;	
						}
						else{
							lsSub.add(inode.getObject());
						}
					}
        		}
        		return lsSub;
			}
			return null;
		}
		catch (Exception e){
			return null;
		}
		
    }

    //sort by ascii
    public List sortList() {

		List ls = new List();
        //Iterator<ListNode> i = this.iterator();
        //ListNode inode;

		int smallerInt = 0;
		int sameInt = 0;
		int intChar = 0;
		Object[] arr = toArray();

		for (int i = 0; i < size; i++){

			for(int j = 0; j < size; j++){
				if (arr[j]	!= null){
					//check smaller
					if (arr[smallerInt] == null){
						smallerInt = j;
					}
					if (((int )arr[j].toString().charAt(0)) < ((int) arr[smallerInt].toString().charAt(0))){
						smallerInt = j;
					}
					else if (((int )arr[j].toString().charAt(0)) == ((int) arr[smallerInt].toString().charAt(0))){
						sameInt = j;
					}
				}
			}
			if (arr[smallerInt] != null){
				ls.add(arr[smallerInt]);
				arr[smallerInt] = null;
			}
			else if (arr[sameInt] != null){
				ls.add(arr[sameInt]);
				arr[sameInt] = null;
			}
		}
		
        return ls;
    }

    //iterator forward
    public Iterator<ListNode> iterator() {
        inode = head;
        return new Iterator<>(){
			
			//is there a next one?
            public boolean hasNext(){
                return inode.next != null;
            }

            //get next
            public ListNode next(){
                if (inode != null){
                    ListNode tmp = inode;
                    inode = inode.next;
                    return tmp;
                }
				else{
                    return null;
                }
            }
        };
    }

    //iterator backwards
    public Iterator<ListNode> iteratorBack() {
        inode = tail;
        return new Iterator<>(){
			
			//is there a next one?
            public boolean hasNext(){
                return inode.before != null;
            }

            //get next
            public ListNode next(){
                if (inode != null){
                    ListNode tmp = inode;
                    inode = inode.before;
                    return tmp;
                }
				else{
                    return null;
                }
            }
        };
    }

    //iterator forward custom start
    public Iterator<ListNode> iterator(ListNode node) {
		if (node != null)
			inode = node;
		else
        	inode = head;

        return new Iterator<>(){
			
			//is there a next one?
            public boolean hasNext(){
                return inode.next != null;
            }

            //get next
            public ListNode next(){
                if (inode != null){
                    ListNode tmp = inode;
                    inode = inode.next;
                    return tmp;
                }
				else{
                    return null;
                }
            }
        };
    }

	//print list
    public void rec(ListNode node) {
		
        out.println(node.toString());
        if (node.next != null) {
            rec(node.next);
        }
    }

}
