import java.io.*;
import java.util.*;

public class StringList implements Iterable<String> {
    /**
     * https://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked%20Lists/code/LinkedList.java
     */
    public class Node {
        public String item;   // One of the items in the list
        public Node next;     // Pointer to the node containing the next item.
        
        public Node(String s, Node n){
        	item = s;
        	next = n;
        }
        						
    }
    

    public Node head;  		// A pointer to the first node in the linked list.
                        			// If the list is empty, the value is null.

	public StringList() {
		head = null;
    }
    

    public String getFirst() {
    	if(head == null){ 
    		return "null";
    	}else{
    	return head.item;
    	}
    }

    public String getLast(){
    	if(head == null) return null;
    	Node tmp = head;
    	while(tmp.next != null)
    		tmp = tmp.next;
    	
    	return tmp.item;
    }
    
    public void addFirst(String item){
    	head = new Node(item, head);
    }
    
    public void addLast(String item){
    	if(head == null)
    		addFirst(item);
    	else{
    		Node tmp = head;
    		while(tmp.next != null)
    			tmp = tmp.next;
    		tmp.next = new Node(item, null);
    	}
    }
    public void clear(){
    	head = null;
    }
    
    public boolean contains(String x){
    	for(String tmp : this)
    		if(tmp.equals(x)) return true;
    	return false;
    }
    /**
     * Returns an array that contains all the elements in the list.
     * If the list is empty, the return value is an array of length zero.
     */
    public String[] getElements() {

        int count;          // For counting elements in the list.
        Node runner;        // For traversing the list.
        String[] elements;  // An array to hold the list elements.

        // First, go through the list and count the number
        // of elements that it contains.

        count = 0;
        runner = head;
        while (runner != null) {
            count++;
            runner = runner.next;
        }

        // Create an array just large enough to hold all the
        // list elements.  Go through the list again and
        // fill the array with elements from the list.

        elements = new String[count];
        runner = head;
        count = 0;
        while (runner != null) {
            elements[count] = runner.item;
            count++;
            runner = runner.next;
        }

        // Return the array that has been filled with the list elements.

        return elements;

    } // end getElements()
    
    public int listLength() {
    	int count = 0;
    	Node runner = head;
    	
    	while(runner != null){
    		count++;
    		runner = runner.next;
    	}
    	return count;
    }
    
    public void printList(){
    	Node current = head;
    	while (current != null) {
    		System.out.print(current.item);	
    		System.out.print("\t");
            current = current.next;
        }
        System.out.println();
    }
    
    public void printToFile(String output) throws IOException {
    	Node current = head;
    	
    	File fout = new File(output);
    	FileOutputStream fos = new FileOutputStream(fout);
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    	     	
       	while (current != null){
    		bw.write(current.item + "  ");
    		//bw.newLine();
    		current = current.next;
     	}
       	bw.close();
    }
	
    
    public Iterator<String> iterator()
    {
       return new LinkedListIterator();
    }

    private class LinkedListIterator  implements Iterator<String>
    {
       private Node nextNode;

       public LinkedListIterator()
       {
          nextNode = head;
       }

       public boolean hasNext()
       {
          return nextNode != null;
       }

       public String next()
       {
          if (!hasNext()) throw new NoSuchElementException();
          String res = nextNode.item;
          nextNode = nextNode.next;
          return res;
       }

       public void remove() { throw new UnsupportedOperationException(); }
    }
}

