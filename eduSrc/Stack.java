package edu;

import java.util.Iterator;

import static java.lang.System.*;

public class Stack implements StackInterface, Iterable<StackNode> {

	
    private StackNode inode; //iterator node
    private int size;

    public StackNode head;
    public StackNode tail;

    /**
     * List
     */
    public Stack() {
        clear();
    }

    //check emptyness
	public boolean isEmpty() {
        return (head == null);
    }

    //get size
	public int size() {
        return size;
    }

    //wipe
	public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    //See the item at the top
    public Object peek(){
        if (tail != null)
            return tail.getObject();
        else
            return null;
    }

    //remove the top item
    public Object pop(){
        if (!isEmpty()){
            StackNode tmp = tail;
            tail = tail.next;

            this.size--;
            return tmp.getObject();
        }
        return null;
    }

    //Push a new item at the top
    public boolean push(Object object){
        try{
            if (isEmpty()){
                head = new StackNode(object);
                tail = head;
            }
            else{
                StackNode tmp = new StackNode(object);
                tmp.next = tail;

                tail = tmp;
            }
            this.size ++;
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    //find and object
    public boolean search(Object object) {
        Iterator<StackNode> i = this.iterator();
        StackNode inode;

        while ((inode = i.next()) != null) {
			//System.out.println(inode);
            if (inode.getObject().toString().equals(object.toString())) {
                return true;
            }
        }
        return false;
    }

    //reverse
    public void reverse(){
        Iterator<StackNode> i = this.iterator();
        StackNode inode;

        StackNode 
        back1 = null, 
        back2 = null;

        int ni = 0;

        while ((inode = i.next()) != null) {
			//System.out.println(inode);

            if (ni == 0){ //first iteration
                this.head = inode;
                back1 = inode;
                ni++;
            }
            else{
                if (ni == 1){  //second iterarion
                    back1.next = null;
                    ni++;
                }

                if (inode.next == null){ //head
                    this.tail = inode;
                }

                back2      = back1;
                back1      = inode;
                back1.next = back2;
            }
        }
    }

    //convert to string
    public String toString(){
        return "Stack{" +
               "head=" + this.head +
               ", tail=" + this.tail +
               '}';
    }

    public void sort(){

        //convert to array and Clear it
        Object[] arr = new Object[this.size()];
        int ni = 0;
        int arrSize = arr.length;

        for (int k = 0; k < arrSize; k++){
            arr[k] = this.pop();
            //System.out.println(arr[k]);
        }

        //Rearange and push them
        int smallerInt = 0;
		int sameInt = 0;
		int intChar = 0;

        for (int i = 0; i < arrSize; i++){

			for(int j = 0; j < arrSize; j++){
				if (arr[j]	!= null){

					//check smaller (i will improve this sort soon)
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

            //push
			if (arr[smallerInt] != null){
				this.push(arr[smallerInt]);
				arr[smallerInt] = null;
			}
			else if (arr[sameInt] != null){
				this.push(arr[sameInt]);
				arr[sameInt] = null;
			}
		}
    }

    //iterator
    public Iterator<StackNode> iterator() {
        inode = tail; //the searchs always start from the tail
        return new Iterator<>(){
			
			//is there a next one?
            public boolean hasNext(){
                return inode.next != null;
            }

            //get next
            public StackNode next(){
                if (inode != null){
                    StackNode tmp = inode;
                    inode = inode.next;
                    return tmp;
                }
				else{
                    return null;
                }
            }
        };
    }

    //iterator custom start
    public Iterator<StackNode> iterator(StackNode node) {
		if (node != null)
			inode = node;
		else
        	inode = tail;

        return new Iterator<>(){
			
			//is there a next one?
            public boolean hasNext(){
                return inode.next != null;
            }

            //get next
            public StackNode next(){
                if (inode != null){
                    StackNode tmp = inode;
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
    public void rec(StackNode node) {
		
        out.println(node.toString());
        if (node.next != null) {
            rec(node.next);
            // <- ;) ->
        }
    }

}
