package model;

import java.io.*;

import controller.GameController;


public class SaveLoad {

	/** save: serializes the object passed in and saves it to a file called game.ser */
	
	public static void save(Object o, GameController gc){
		try
		{
			File f = new File("src/controller/goods.ser");
	         FileOutputStream fileOut = new FileOutputStream(f);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(o);
	         out.close();
	         fileOut.close();
	         
	 		PrintWriter output = 
 			new PrintWriter(new FileWriter("src/controller/gametxt.txt"));
	 		output.println(gc.getPlayer().toString());
	 		output.close();
	      }
		catch(IOException i)
	      {
	         // i.printStackTrace();
	      }

	}
	
	/** load: loads the serialized game and returns the game object that is loaded */
	
	public static Object load(){
		Object o = null;
		try
	    {
	        FileInputStream fileIn = new FileInputStream("src/controller/goods.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        o = in.readObject();
	        in.close();
	        fileIn.close();
	        System.out.println("loaded game: " + fileIn.toString());
	    }
		 
		catch(IOException i)
        {
            //i.printStackTrace();
            return null;
        } 
		
		catch (ClassNotFoundException e) {
			System.out.println("Could not find class!");
		}
		
		return o;
		
	}
	

}
