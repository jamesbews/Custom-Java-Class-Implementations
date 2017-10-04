import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Implements Queue
 * Adapted From: http://algs4.cs.princeton.edu/13stacks/Queue.java.html
 * @author James
 * @version 1.0
 * @since March 12, 2017
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item>{

    private Node<Item> first;    
    private Node<Item> last;     
    private int n;               

    /**
     * Generic Node class for each Node in the queue     *
     * @param <Item>
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Default contructor
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in the queue.
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to the queue.
     * @param  item to add to the queue
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns the item at the front of the queue
     * @return 
     * @throws NoSuchElementException if the queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   
        return item;
    }

    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return 
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    @SuppressWarnings("hiding")
	private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
        	return current != null; 
        }
        public void remove() {
        	throw new UnsupportedOperationException(); 
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
}
