package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 4
 *  returns their name and a
 *  modified string 
 *  
 *  @author Hayden
 *  @version 1.1
 */
public class Person4 {
  /** Holds the persons real name */
  private String name;
    /**
     * The constructor, takes in the persons
     * name
     * @param pname the person's real name
     */
  public Person4(String pname) {
    name = pname;
  }
    /**
     * This method should take the string
     * input and return its characters rotated
     * 1 position.
     * given "gtg123b" it should return
     * "tg123bg".
     *
     * @param input the string to be modified
     * @return the modified string
     */
    private static String calc(String input) {
    	String output = "";
    	for(int i = 0; i < input.length() - 1; i++){
    		output += input.charAt(i + 1);
    	}
    	output += input.charAt(0);
      return output;
    }
    
    /**
     * Return a string rep of this object
     * that varies with an input string
     *
     * @param input the varying string
     * @return the string representing the 
     *         object
     */
    public String toString(String input) {
      return name + calc(input);
    }
}