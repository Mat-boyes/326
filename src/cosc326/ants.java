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
    
     //   private String dna;
        private Point loc;
        private HashMap<Point, Character> visited;
        private int lastDir;
        private ArrayList<Gene> genes;

	public ants(){
	//	dna = "";
		loc = new Point(0,0);
		visited = new HashMap<Point, Character>();
		lastDir = 0; //0 = north, 1= east, 2=south, 3=west
		genes = new ArrayList<Gene>(); //list of genes
	}
	
	/*
	 * Main method creates instance of ants and calls the start method on it.  
	 */
	public static void main(String[]args){
		new ants().start(); 
	}
	
	/*
	 * Start the program by scanning in input and calculating the final location of the ant. 
	 */
	public void start(){
		Scanner sc = new Scanner(System.in);
		//int count = 0; 
        while(sc.hasNextLine()){
            if(!sc.hasNextInt()){ 
            	String s = sc.nextLine(); 
            	
            	if(s.contains("#")){
            		System.out.println("contains a #");
            		//should skip this line. 
            	}else{
            		Scanner sc2 = new Scanner(s);
            		char state = sc2.next().charAt(0);
                	String actions = sc2.next();
                	String result  = sc2.next();
                   
                //	System.out.println("Test: " + state + " " + actions + " " + result);

               	 //  Gene gene = new Gene(s.charAt(0), s.substring(2,6), s.substring(6,11));
                	Gene gene = new Gene(state, actions, result);
               	   	genes.add(gene);
            	}
            	
            }else{ //If we find an integer, we use it as the number of moves we calculate. 
            	// System.out.println(dna);
                 calcMove(sc.nextInt());
             }             
            //}else{
                 //sc.nextLine();
            //}
        //    System.out.println("Count " + count);
          //  count++; 
		}
		sc.close();          
	}
	
		/*
		 * Calculate the location in which we end up after stop number of moves 
		 */
       public void calcMove(int stop){
            char state;
         /*   Scanner scan = new Scanner(dna);
            while(scan.hasNextLine()){ //scan each line of the DNA 
            	String s = scan.nextLine();
            	Gene gene = new Gene(s.charAt(0), s.substring(1,5), s.substring(5,9));
            	genes.add(gene);
            }
            scan.close();*/
            
           //System.out.println("Initial location: " + loc);
            
            for(int i = 0; i < stop; i++){ //for each step we need to make 
                if(visited.containsKey(loc)){ //if the location HAS been visited
                    state = visited.get(loc); //get the state of that location 
                    //System.out.println("visited");
                }else{//if it has NOT been visited
                	state = (genes.get(0)).getCurrentState(); //Just use the first state
                	//System.out.println("not visited");
                }
                //pass the state of the location to movement
                movement(state);
            }

            for (Gene i: genes){
				System.out.println(i.currState + " " + i.actions + " " + i.nextStates);
			}
			System.out.println(stop);
			System.out.println("# " + (int)loc.getX() + " "+ (int)loc.getY());
		//	System.out.println();
	       
				
		//Resetting?
	   //   dna = "";
	      loc = new Point(0,0);
	      visited = new HashMap<Point, Character>();
	      lastDir = 0; //0 = north, 1= east, 2=south, 3=west
	      genes = new ArrayList<Gene>(); //list of genes
        }
       
       /*
        * This method takes in the state of the current location and finds the new state 
        * and which direction to move next. 
        */
       public void movement(char state){  	   
    	   //Find the gene that refers to this state of interest
    	   Gene g = new Gene();
    	   for(Gene x: genes){
    		   if(x.getCurrentState() == state){
    			   g = x;  //this is our gene of interest
    		   }
    	   }
    	 
    	   char nextDirection = g.getAction(lastDir); //next direction to go
    	   char newState = g.getNextState(lastDir); //new state of this location
    	   
           visited.put(loc, newState); //update this location to the new state in the hashmap
           
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
        }
        
       /*
        * This class creates a data structure Gene which holds information such
        * as what state that gene refers to, what action to take and what the next state 
        * should be depending on the direction you have arrived from. 
        */
       public class Gene{
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
