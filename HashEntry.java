/**
 * Implementation of a entry for a hash table that contains the string a sequence of ascii codes and unique hash code
 * @author James Bews
 * @version 1.0
 * @since April 9, 2017
 *
 */
public class HashEntry {
	private String key; //Unique hash code for each string
	private String value; //Value of string as a sequence of ascii codes
	
	/**
	 * Contsructor
	 * @param key
	 * @param value
	 */
	HashEntry(String key, String value){
		
		this.key = key;
		this.value = value;		
	}
	
	/**
	 * Returns hash entry key
	 * @return: key
	 */
	public String getKey(){
		return key;
	}
	
	/**
	 * Returns hash entry value
	 * @return: value
	 */
	public String getValue(){
		return value;
	}
}
