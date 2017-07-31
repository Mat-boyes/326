

/*
 * Etude 1: Ants on a Plane
 * 
 * Author: Mathew Boyes
 * 
 */

import java.util.*;


public class pokerHands{
	
	private String[] hand;
	private String line, output;
	
	
	public pokerHands(){
		hand = new String[5];
		
	}
	
	public static void main(tring[]args){
		Scanner scan = new Scanner(System.in);
		
		
		//Hand hand = new Hand();
		
		line = scan.nextLine();
		
		setupHand(checkValid());
		
		
		System.out.println(output);
	}
	
	public String checkValid(){
		
		
		
		if(line.contains('-')){
			return "-";
		}
		
		if(line.contains('/')){
			return "/";
		}
		
		if(line.contains(' ')){
			return " ";
		}
		return "invalid: ";
		
	}
	
	public void setupHand(String s){
		int start = 0, end = 0, i = 0;
		
		while(start < line.length() || i < 5){
			end = line.indexOf(s, start) - 1;
			hand[i] = line.substring(start, end);
			start = end+1;
			i++;
		}
		
	}
}
