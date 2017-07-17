/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc326;


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
    
        private String dna;
        private Point loc = new Point(0,0);
        private HashMap visited = new HashMap();
	
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
                            for (int i = 0; i < 3; i++){
                                dna += sc.next();
                            }
                            dna += "\n";
                        }else{
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
            int lastDir;
            Object state;
            
            Scanner scan = new Scanner(dna);
            while(scan.hasNextLine()){
                statesNum++;
            }
            scan.reset();
            char[] states = new char[statesNum];
            
            for (int i = 0; i < statesNum; i++){
                states[i] = scan.next().charAt(0);
                scan.nextLine();
            }
            
            scan.reset();
            lastDir = 0;
            for(int i = 0; i < stop; i++){
                if(visited.get(loc) != null){
                    state = visited.get(loc);
                }
                
                movement();
                
                visited.put(loc, states[lastDir]);
                             
                
            }
                
            
            
        }
        
        public void movement(){
            loc.move(0, 0);
        }
	
}