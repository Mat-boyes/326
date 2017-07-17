
/*
 * Etude 1: Ants on a Plane
 * 
 * Authors: 
 */

import java.awt.Point;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ants {
    
	//TODO: add to constructor 
        private String dna = "";
        private Point loc = new Point(0,0);
        private HashMap<Point, Character> visited = new HashMap<Point, Character>();
        private int lastDir = 0;

	public ants(){
	}
	
	public static void main(String[]args){
		new ants().start(); 
	}
	
	public void start(){
		Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            if(!sc.hasNext("#")){
                if(!sc.hasNextInt()){
                	//TODO: double check this doesnt need to be hasNext
                   for (int i = 0; i < 3; i++){ 
                       dna += sc.next();
                   }
                   dna += "\n";
                }else{
                     //scanned in an integer  
                	 System.out.println(dna);
                	 
                     calcMove(sc.nextInt());
                 }             
           }else{
                 sc.nextLine();
           }
		}
		sc.close(); 
                
	}
		//calculate the location in which we end up after stop moves. 
       public void calcMove(int stop){
        //    int statesNum = 0;
            char state;
           // char[] states = new char[statesNum];
            ArrayList<Character> states = new ArrayList<Character>(); 
            
            Scanner scan = new Scanner(dna);
            while(scan.hasNextLine()){ //scan each line of the DNA 
            	states.add(scan.nextLine().charAt(0));
            	//TODO: make a data structure
              //  statesNum++;
            }
            scan.close(); 
            
           
            for(int i = 0; i < stop; i++){ //for each step we need to make 
                if(visited.get(loc) != null){ //if HAS been visited
                	
                    state = visited.get(loc); //get the state of that location 
                    System.out.println(state);
                }
                
                //movement(state, lastDir);
                
                //UPDATE 
                visited.put(loc, states.get(lastDir)); //add the state of the location to the hashmap
            }
                
            System.out.println(loc);
            
        }
        
      /*  public void movement(char state, int lastDir){
            
            int starter =dna.indexOf(state);
            
            starter += lastDir +1;
            if(dna.charAt(starter) == "N"){
                loc.move(0, 0);
                lastDir = 0;
            }else if((dna.charAt(starter)).equals("E")){
                loc.move(0, 0);
                lastDir = 1;
            }else if(dna.charAt(starter).equals("S")){
                loc.move(0, 0);
                lastDir = 2;
            }else{
                loc.move(0, 0);
                lastDir = 3;
            }
            
            loc.move(0, 0);
        }   
	*/
}