/**
 * Implements a FIFO stack data structure
 * Adapted from: http://pages.cpsc.ucalgary.ca/~aniruddha.chattoraj/TA_319_W17.html
 * @version 1.0
 * @since March 25, 2017 *
 */
public class Stack {
	
	private Node top;
	private int size;
	
	class Node{
	    public int element;
	    public Node next;
	    
	    //Constructors
	    public Node (int e, Node n) {
	        element = e;
	        next = n;
	    }

	    public Node (int e) {
	        element = e;
	        next = null;
	    }
	}
	/**
	 * Default constructor
	 */
	public Stack(){
		top=null;
		size = 0;
	}
	/**
	 * Checks if stack is empty
	 * @return
	 */
	public boolean isEmpty(){
		return (top == null);
	}
	/**
	 * Empties stack
	 */
	public void clear () {
		top = null;
		size = 0;
	}
	/**
	 * Adds item to the top of stack
	 * @param e
	 */
	public void push (int e){
		top = new Node(e, top);
		size++;
	}
	/**
	 * Returns item from the top of stack
	 * @return
	 */
	public int pop(){
		if(isEmpty()){
			System.out.println("Error: Cannot pop, stack is empty");
		}
		
		int topItem = top.element;
		top = top.next;
		size--;
		
		return topItem;
	}
	/**
	 * Returns item on top of stack without removing itm from the stack
	 * @return
	 */
	public int peek(){
		if(isEmpty()){
			System.out.println("Cannot peek, stack is empty");
		}	
		return top.element;
	}
	/**
	 * Returns size of the stack
	 * @return
	 */
	public int size() {
		return size;
	}
}
