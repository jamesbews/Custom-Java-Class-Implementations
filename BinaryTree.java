import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Implements a Binary Search Tree
 * Adapeted From: Drozdek, A. (2001). Data structures and algorithms in Java 1st Edition
 * @author James Bews
 * @version 1.0
 * @since March 12, 2017
 */
public class BinaryTree {
	public Node root; //Pointer to root node
	public String depthFileOut; //Stores depth first traversal output file name
	public String breadthFileOut; //Stores breadth first traversal output file namw
	
	/**
	 * Default constructor
	 */
	public BinaryTree(){
		this.root = null;
	}
	
	/**
	 * Finds node to delete and deletes based on 3 cases. No children, one child, two children
	 * @param input: Node to be deleted
	 * @return
	 */
	public boolean delete(String input){
		Node temp = new Node(input);
		Node parent = root;
		Node current = root;
		boolean isLeft = false;
		//Find node to delete
		while(current.studentName.compareTo(temp.studentName) == 0){
			parent = current;
			if(current.studentName.compareTo(temp.studentName) > 0){
				isLeft = true;
				current = current.left;
			}else{
				isLeft = false;
				current = current.right;
			}
			if(current == null){
				return false;
			}
		}
		//Node has been found
		//Case 1: No children
		if(current.left == null && current.right == null){
			if(current == root){
				root = null;
			}
			if(isLeft = true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2: One child
		else if(current.right == null){
			if(current == root){
				root = current.left;
			}else if(isLeft){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		//Case 3: Two children
		else if(current.left != null && current.right != null){
			Node next = getNext(current);
			if(current == root){
				root = next;
			}else if(isLeft){
				parent.left = next;
			}else{
				parent.right = next;
			}
			next.left = current.left;
		}
		return true;
	}
	/**
	 * Gets next successive node
	 * @param deleteNode
	 * @return
	 */
	public Node getNext(Node deleteNode){
		Node next = null;
		Node nextParent = null;
		Node current = deleteNode.right;
		while(current != null){
			nextParent = next;
			next = current;
			current = current.left;
		}
		if(next != deleteNode.right){
			nextParent.left = next.right;
			next.right = deleteNode.right;
		}
		return next;
	}
	/**
	 * Inserts a given node in proper position based on the Student Name key
	 * @param input
	 */
	public void insert(String input){
		Node newNode = new Node(input);
		if(root == null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(newNode.studentName.compareTo(current.studentName) < 0){
				current = current.left;
				if(current == null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current == null){
					parent.right = newNode;
					return;
				}
			}
		}
	}
	/**
	 * Set file name for output files
	 * @param depth
	 * @param breadth
	 */
	public void setFileName(String depth, String breadth){
		depthFileOut = depth;
		breadthFileOut = breadth;
	}
	/**
	 * Depth first in order traversal. Prints result to output file
	 * @param root
	 */
	public void depthFirst(Node root){
		if(root != null){
			depthFirst(root.left);
			depthToFile(root.studentNumber + root.studentName + "\t\t" + root.department + root.program + "\t" + root.year);
			depthFirst(root.right);
		}
	}
	/**
	 * Prints result of depth first traversal to file
	 * @param input
	 */
	public void depthToFile(String input){
		try{
			File fout = new File(depthFileOut);
			if(!fout.exists()){
				fout.createNewFile();
			}
			FileWriter fw = new FileWriter(fout, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			
			bw.write(input);
			bw.newLine();
			bw.close();
			
		}catch (IOException e){
			System.err.println("Error writing to file" + e.getMessage());
		}
	}
	/**
	 * Breadth first traversal. Prints result to output file
	 * @param root
	 */
	public void breadthFirst(Node root){
		Queue<Node> q = new Queue<Node>();
		if(root == null)
			return;
		q.enqueue(root);
		while(!q.isEmpty()){
			Node n = (Node) q.dequeue();
			breadthToFile(n.studentNumber + n.studentName + "\t\t" + n.department + n.program + "\t" + n.year);
			if(n.left != null)
				q.enqueue(n.left);
			if(n.right != null)
				q.enqueue(n.right);
		}
		
	}
	/**
	 * Prints result of breadth first traversal
	 * @param input
	 */
	public void breadthToFile(String input){
		try{
			File fout = new File(breadthFileOut);
			if(!fout.exists()){
				fout.createNewFile();
			}
			FileWriter fw = new FileWriter(fout, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			
			bw.write(input);
			bw.newLine();
			bw.close();
			
		}catch (IOException e){
			System.err.println("Error writing to file" + e.getMessage());
		}
	}
}
/**
 * Creates node for the Binary Search Tree
 */
class Node{
	int studentNumber;
	int year;
	String studentName;
	String department;
	String program;
	
	Node left;
	Node right;
	public Node(String input){
		deconstruct(input);
		left = null;
		right = null;
	}
	/**
	 * Breaks apart input file line string into different variables that are stored in each Node
	 * @param decon
	 */
	public void deconstruct(String decon){
		
		char[] test = decon.toCharArray();
		
		int k = 1;
		//Get student number
		char[] num = new char[7];
		while(k < 8){
			num[k - 1] = test[k];
			k++;
		}
		String temp = String.valueOf(num);
		studentNumber = Integer.parseInt(temp);
		num = null; // Free array
		
		//Get student name
		int i = 0;
		char[] nm = new char[10];
		while(test[k] != ' '){			
			nm[i] = test[k];
			k++;
			i++;			
		}
		studentName = String.valueOf(nm);
		nm = null; //Free array
		//Skip white space
		while(test[k] == ' '){
			k++;
		}
		//Get home department
		i = 0;
		char[] dep = new char[4];
		while(k < 37){
			dep[i] = test[k];
			k++;
			i++;
		}
		department = String.valueOf(dep);
		dep = null; //Free array
		i = 0;
		//Get program
		char[] prog = new char[4];
		while(test[k] != ' '){
			prog[i] = test[k];
			k++;
			i++;
		}
		program = String.valueOf(prog);
		prog = null;//Free array
		//Skip white space
		while(test[k] == ' '){
			k++;
		}
		//Get year
		String tmp = String.valueOf(test[k]);
		year = Integer.parseInt(tmp);
		
	}
}