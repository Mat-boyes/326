
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
        private int lastDir = 0; //0 = north, 1= east, 2=south, 3=west
        private ArrayList<Gene> genes = new ArrayList<Gene>();

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
                	// System.out.println(dna);
                	 
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
       //     ArrayList<Character> states = new ArrayList<Character>();

            Scanner scan = new Scanner(dna);
            while(scan.hasNextLine()){ //scan each line of the DNA 
            	String s = scan.nextLine();
            //	char c = s.charAt(0);
            	//String s1 = s.substring(1,4);
            	//String s2 = s.substring(5,8);
            //	states.add(s.charAt(0));
            	
            	
            	//TODO: make a data structure
              //  statesNum++;
            	Gene gene = new Gene(s.charAt(0), s.substring(1,4), s.substring(5,8));
            	genes.add(gene);
            }
            //lastDir
      /*      for(Gene g : genes){
            	System.out.println(g.getAction(lastDir)+" " + g.getNextState(lastDir));
            }*/
            scan.close(); 
            
            System.out.println("Initial location: " + loc);
            
           
            for(int i = 0; i < stop; i++){ //for each step we need to make 
                if(visited.containsKey(loc)){ //if HAS been visited
                    state = visited.get(loc); //get the state of that location 
                 //   System.out.println(state);
                    System.out.println("visited");
                }else{//if it has NOT been visited, just use first state. 
                	state = (genes.get(0)).getCurrentState();
                	System.out.println("not visited");
                }
             //   System.out.println("State: " + state);
                //pass the state of the location and the direction coming from
                movement(state);
   
                //UPDATE = state of new location is the currentstate of the last direction?          
            }
                
            System.out.println("End location: " + loc);
            
        }
       
       public void movement(char state){
           //input state is the state to react to. 
    	   //input lastDir is the direction I've come from 0-3
    	   
    	   //need to get the gene where the currentState matches state 
    	   Gene g = new Gene();
    	   for(Gene x: genes){
    		   if(x.getCurrentState() == state){
    			   g = x;  //this is our gene of interest
    		   }
    	   }
    	   char nextDirection = g.getAction(lastDir);
    	   char newState = g.getNextState(lastDir);
    	 //update hashtable with new state
           visited.put(loc, newState); //add the state of the location to the hashmap
    	   System.out.println("Move in direction "+ nextDirection);
    	   System.out.println("New state of "+ loc + " is " + newState);
            if(nextDirection == 'N'){  //if came from North
            	//go in direction g.actions(lastDir) and set state to g.
                loc.translate(0, 1);
                lastDir = 0;
            }else if(nextDirection == 'E'){
                loc.translate(1, 0);
                lastDir = 1;
            }else if(nextDirection == 'S'){
                loc.translate(0, -1);
                lastDir = 2;
            }else{
                loc.translate(-1, 0);
                lastDir = 3;
            } 
            
            
        }
        
      /*public void movement(char state, int lastDir){
            
    	   //index of current state in dna
            int starter =dna.indexOf(state);
            
            //adds last direction to the state 
            starter += lastDir +1;
            //char c = dna.charAt(starter); 
            if(dna.charAt(starter) == 'N'){
                loc.move(0, 1);
                lastDir = 0;
            }else if(dna.charAt(starter) == 'E'){
                loc.move(1, 0);
                lastDir = 1;
            }else if(dna.charAt(starter) == 'S'){
                loc.move(0, -1);
                lastDir = 2;
            }else{
                loc.move(-1, 0);
                lastDir = 3;
            }
            
            loc.move(0, 0);
        }    */
       
       public class Gene{
    	   //(char currState, String actions, String nextStates)
    	   private char currState;
    	   private String actions;
    	   private String nextStates;
    	   
    	   public Gene(){   
    	   }
    	   
    	   public Gene(char currState, String actions, String nextStates){
    		   this.currState = currState;
    		   this.actions = actions;
    		   this.nextStates = nextStates; 
    	   }
    	   
    	   public char getCurrentState(){
    		   return currState; 
    	   }
    	   
    	   public char getAction(int position){
    		   //0 = N, 1= E, 2 = S, 3 = W
    		   return actions.charAt(position);
    	   }
    	   
    	   public char getNextState(int position){
    		   return nextStates.charAt(position);
    	   }
       }
  
       
      
}

