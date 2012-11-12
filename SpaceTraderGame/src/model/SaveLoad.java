package model;

import java.io.*;


public class SaveLoad implements java.io.Serializable{

	/**
	 * has save and load game methods
	 * p.s. if there are any instance variables or any data that we don't want to be saved, we must
	 * go into the class where it is and declare it transient. 
	 */
	private static final long serialVersionUID = 1L;
	
	/** save: serializes the object passed in and saves it to a file called game.ser */
	
	public static void save(Object o){
		try
		{
	         FileOutputStream fileOut =
	         new FileOutputStream("game.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(o);
	         out.close();
	         fileOut.close();
	      }
		catch(IOException i)
	      {
	          i.printStackTrace();
	      }

	}
	
	/** load: loads the serialized game and returns the game object that is loaded */
	
	public static Object load(){
		Object o = null;
		try
	    {
	        FileInputStream fileIn = new FileInputStream("game.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        o = in.readObject();
	        in.close();
	        fileIn.close();
	    }
		 
		catch(IOException i)
        {
            i.printStackTrace();
            return null;
        } 
		
		catch (ClassNotFoundException e) {
			System.out.println("Could not find class!");
		}
		
		return o;
		
	}
	

}
