
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
                	 System.out.println(dna);
                     calcMove(sc.nextInt());
                 }             
           }else{
                 sc.nextLine();
           }
		}
		sc.close(); 
                
	}

       public void calcMove(int stop){
            int statesNum = 0;
            char state;
            
            Scanner scan = new Scanner(dna);
            while(scan.hasNextLine()){
                statesNum++;
                System.out.println(statesNum);
            }
            scan.reset();
            char[] states = new char[statesNum];
            
            for (int i = 0; i < statesNum; i++){
                states[i] = scan.next().charAt(0);
                scan.nextLine();
            }
            
            scan.reset();
            
            for(int i = 0; i < stop; i++){
                if(visited.get(loc) != null){
                    state = visited.get(loc);
                }
                
                movement(state, lastDir);
                
                visited.put(loc, states[lastDir]);
                             
                
            }
                
            
            
        }
        
        public void movement(char state, int lastDir){
            
            int starter =dna.indexOf(state);
            
            dna.
            
            loc.move(0, 0);
        } 
	
}