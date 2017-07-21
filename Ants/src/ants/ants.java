//package ants;

/*
 * Etude 1: Ants on a Plane
 * 
 * Authors: Mika Smith, Mathew Boyes
 * 
 * This program takes in lines of input as genes that encode for behaviour 
 * when a location has a certain state and what to update that state to, as well
 * as which direction to go next. The ant will start at location 0,0 and after n
 * moves, the program will print the final location. 
 */

import java.awt.Point;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ants {
    
        private static Point loc;
        private static HashMap<String, Character> visited;
        private static int lastDir;
        private static ArrayList<Gene> genes;

	public ants(){
		loc = new Point(0,0);
		visited = new HashMap<String, Character>();
		lastDir = 0; //0 = north, 1= east, 2=south, 3=west
		genes = new ArrayList<Gene>(); //list of genes
	}
	
	/*
	 * Main method scans in input for the DNA of each ant scenario.   
	 */
	public static void main(String[]args) throws FileNotFoundException{
		File myfile = new File("H:/326/326/Ants/src/ants/states.txt");
		Scanner sc = new Scanner(myfile);
		//Scanner sc = new Scanner(System.in);
		ants ant = new ants(); 
		while(sc.hasNextLine()){
			String s = sc.nextLine(), hold = "";
			if(s.equals("")){
				//System.out.println("this isnt working");
				ant = new ants();
			}else if(Character.isDigit(s.charAt(0))){ //If we scan in a number, we have finished scanning DNA for this scenario. 
				for(int i = 0; i < s.length(); i++){
					hold += s.charAt(i);
					
				}
				s = hold;
				int numMoves = Integer.parseInt(s);  
            	calcMove(numMoves);
            	
            	printGenes(); 
	     		System.out.println(numMoves);
	     		System.out.println("# " + (int)loc.getX() + " "+ (int)loc.getY());
	     		System.out.println();
	     		
	     		 //Reset the ant for a new scenario
			}
			else if(!s.contains("#")){ //Ignore lines with # comments
        		Scanner sc2 = new Scanner(s);
        		char state = sc2.next().charAt(0);
            	String actions = sc2.next();
            	String result  = sc2.next();
            	sc2.close();
            	
            	Gene gene = new Gene(state, actions, result); //Create a new gene
           	   	genes.add(gene); //Add it to the DNA of this ant scenario
        	}
		}
		sc.close();   
	}
	
	/*
	 * Prints the contents of an ant's genes. 
	 */
	public static void printGenes(){
		if(genes.isEmpty()){
			System.out.println("No genes!");
		}
		
		 for (Gene i: genes){
  			System.out.println(i.currState + " " + i.actions + " " + i.nextStates);
  		}
	}

		/*
		 * Calculate the location in which we end up after stop number of moves 
		 */
       public static void calcMove(int numMoves){
            char state;
       
            for(int i = 0; i < numMoves; i++){ //for each step we need to make 
					String location = Integer.toString(loc.x) + " " + Integer.toString(loc.y);
                if(visited.containsKey(location)){ //if the location HAS been visited
						 //System.out.println(visited.get(loc));
                    state = visited.get(location); //get the state of that location 
						  //System.out.println("visited " + i);
                }else{//if it has NOT been visited
						 //System.out.println("not visited " + i);
						 //System.out.println((genes.get(0)).getCurrentState());
                	state = (genes.get(0)).currState; //Just use the first state
                }
					 //System.out.println(state);
                //pass the state of the location to movement
                movement(state);
					 //System.out.println(visited.toString());
            }
				//System.out.println(visited.toString());
        }
       
       /*
        * This method takes in the state of the current location and finds the new state 
        * and which direction to move next. 
        */
       public static void movement(char state){  	   
    	   //Find the gene that refers to this state of interest
    	   Gene g = new Gene();
    	   for(Gene x: genes){
    		   if(x.getCurrentState() == state){
    			   g = x;  //this is our gene of interest
    		   }
    	   }
    	 
    	   char nextDirection = g.getAction(lastDir); //next direction to go
			//System.out.println(lastDir);
    	   char newState = g.getNextState(lastDir); //new state of this location
				
			String location = Integer.toString(loc.x) + " " + Integer.toString(loc.y);
           visited.put(location, newState); //update this location to the new state in the hashmap
           //System.out.println( loc + " is at state: "+visited.get(loc));
        /* Debugging Info
         
           System.out.println("Arrived in direction "+ lastDir);
           System.out.println("Next direction is "+ nextDirection);
           System.out.println("Old state was "+ state);
    	   System.out.println("New state of "+ loc + " is " + newState);
    	   System.out.println();
    	*/ 
    	   
            if(nextDirection == 'N'){  //if going North
                loc.translate(0, 1); //move the ant north
                lastDir = 0; //set the lastDir to north
            }else if(nextDirection == 'E'){ //if going East 
                loc.translate(1, 0);
                lastDir = 1;
            }else if(nextDirection == 'S'){ //if going South
                loc.translate(0, -1);
                lastDir = 2;
            }else{ //If going west
                loc.translate(-1, 0);
                lastDir = 3;
            } 
				//System.out.println(loc.toString());
        }
        
       /*
        * This class creates a data structure Gene which holds information such
        * as what state that gene refers to, what action to take and what the next state 
        * should be depending on the direction you have arrived from. 
        */
       public static class Gene{
    	   private char currState;
    	   private String actions;
    	   private String nextStates;
    	   
    	   //Empty constructor
    	   public Gene(){   
    	   }
    	   
    	   //Construct variables
    	   public Gene(char currState, String actions, String nextStates){
    		   this.currState = currState;
    		   this.actions = actions;
    		   this.nextStates = nextStates; 
    	   }
    	   
    	   //Return which state this gene refers to. 
    	   public char getCurrentState(){
    		   return currState; 
    	   }
    	   
    	   //Get the action that corresponds to what direction you've arrived from
    	   public char getAction(int position){
    		   //0 = N, 1= E, 2 = S, 3 = W
    		   return actions.charAt(position);
    	   }
    	   
    	   //Get the new state for that location
    	   public char getNextState(int position){
    		   return nextStates.charAt(position);
    	   }
       }   
}
