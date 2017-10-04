/**
 * Implementation of a hashtable utilizing an array structure and a custom hash function
 * Adapted from: http://algs4.cs.princeton.edu/34hash/
 * @author James Bews
 * @version 1.0
 * @since April 9, 2017
 */

public class HashTable {
	/**
	 * Size of hash table
	 */
	private final static int TABLE_SIZE = 15907;
	/**
	 * Number of records in the input file to be stored in table
	 */
	private static int recordCount;
	/**
	 * Number of reads for a particular search record
	 */
	private static int currentReads;
	/**
	 * Overall total number of reads for entire search
	 */
	private static int totalReads = 0;
	/**
	 * Longest chain in search
	 */
	private static int longestChain = 0;
	/**
	 * Hash table implemented as an array
	 */
	HashEntry[] hashTable;
	
	/**
	 * Default Constructor
	 */
	HashTable() {
		hashTable = new HashEntry[TABLE_SIZE];
		for(int i = 0; i < hashTable.length; i++){
			hashTable[i] = null; //Set each array element to null
		}
	}
	
	/**
	 * Finds key in hash table that matches the corresponding intput key
	 * @param key: Search key
	 * @return: Found key
	 */
	public String getEntry(String key){
		
		int hash;
		currentReads = 0;
		
		hash = getHashCode(key);
		currentReads++;
		totalReads++;
		
		while (hashTable[hash] != null && !hashTable[hash].getKey().equals(key)){
			hash = (hash + 1) % TABLE_SIZE; //Linear probing
			
			totalReads++;
			currentReads++;
		}
		
		setLongestChain(currentReads);
		
		if (hashTable[hash] == null){
			return null;
		}else{
			return hashTable[hash].getKey(); //Returns found string that matches search key
		}
		
	}
	
	/**
	 * Takes an input string, assigns it a specific hash code and inputs it into a hash table
	 * @param key: String to be inout in hash table
	 */
	public void inputEntry(String key) {
		
		int hash;
		String value;
		
		hash = getHashCode(key);

		value = getStringValue(key);

		
		while (hashTable[hash] != null && hashTable[hash].getKey() != key){
			hash = (hash + 1) % TABLE_SIZE; //Linear probing to find entry position
		}
		hashTable[hash] = new HashEntry(key, value);	
	}
	
	/**
	 * Converts a string to a sequence of ascii codes, applies it to the hash function and returns the hash code
	 * @param key String to have hash code calculated for 
	 * @return: hash code
	 */
	public int getHashCode(String key){
		int hashCode = 0;
		
		for(int i = 0; i < key.length(); i++){		
			hashCode = (31 * hashCode + key.charAt(i)) % TABLE_SIZE; //Hash function
		}
		
		
		return hashCode;
		
	}
	
	/**
	 * Creates a string of the sequence of the ascii code of each respective char of the given string
	 * @param key: String to have ascii codes accessed from
	 * @return String of ascii codes
	 */
	public String getStringValue(String key){
		String rv;
		StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0; i < key.length(); i++){
			int ascii = (int)key.charAt(i);
			sb.append(ascii);
		}
		
		rv = String.valueOf(sb);
		
		return rv;
	}
	/**
	 * Set number of records in the input file
	 * @param count: Number of records in input file
	 */
	public void setRecordCount(int count){
		recordCount = count;
	}
	/**
	 * Set the longest chain variable
	 * @param count: Number of reads for a specific search
	 */
	public void setLongestChain(int count){
		if(longestChain < count){
			longestChain = count;
			return;
		}else{
			return;
		}
	}
	/**
	 * Returns longest chain
	 * @return: longest chain
	 */
	public int getLongestChain(){
		return longestChain;
	}
	/**
	 * Returns the load factor
	 * @return: Load factor
	 */
	public double getLoadFactor(){
		double rv = (double)recordCount / (double)TABLE_SIZE;
		rv *= 100.00;
		
		return rv;
	}
	/**
	 * Returns average number of reads
	 * @return: average number of reads
	 */
	public double getAverageReads(){
		double rv = (double)totalReads / (double)recordCount;
		return rv;
	}
	/**
	 * Returns hash efficiency
	 * @return: Hash efficiency
	 */
	public double getHashEfficiency(){
		double rv = getLoadFactor();
		double av = getAverageReads();
		rv = rv / av;
		rv *= 100.00;
				
		return rv;
	}
}
