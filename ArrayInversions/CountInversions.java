/**
*	Class that counts the number of inversions in a given array
*
*/

import java.io.*;
import java.util.Scanner;

class CountInversions {
	
	public static final int MAXNUMBERS = 100000;

	public static void main(String[] args){
	
		if(args.length < 1){
			System.out.println("Insufficient arguments!!! Correct useage : java"
							   + " CountInversions <Inputfilename>");
			System.exit(0);
		}
		int[] array = new int[MAXNUMBERS];
		Scanner s = null;
		try{
			s = new Scanner(new File(args[0]));
			int i =0;
			while(i < MAXNUMBERS && s.hasNext()){
				String temp = s.next();
				//System.out.println(temp);
				array[i++] = Integer.parseInt(temp);
			}
		}
		catch(FileNotFoundException e1){
			System.out.println("File not found !!!");
			e1.printStackTrace();
		}
		catch(NumberFormatException e2){
			System.out.println("Invalid integer value !!!");
			e2.printStackTrace();
		}
		finally{
			if( s != null)
				s.close();
		}
		
		System.out.println("Number of Inversions : " + 
							inversionCount(array,MAXNUMBERS));
	}
	
	public static long inversionCount(int[] array, int size){
		long inversions = 0;
		
		if(size ==1)
			return inversions;
		int sizeP = (int)Math.floor(size/2.0);
		int sizeQ = (int)Math.ceil(size/2.0);
		int[] p = new int[sizeP];
		int[] q = new int[sizeQ];
		
		for(int i=0; i < sizeP; i++)
			p[i] = array[i];
			
		for(int i=0; i < sizeQ; i++)
			q[i] = array[sizeP+i];
		
		inversions += inversionCount(p,sizeP);
		inversions += inversionCount(q,sizeQ);
		inversions += mergeArrays(p,q,array);		
		
		return inversions;
	}
	
	public static long mergeArrays(int[] p, int[] q, int[] array){
		long inversions = 0;
		
		int i = 0;
		int j = 0;
		int pos = 0;
		while(i < p.length && j < q.length){
			if(p[i] <= q[j]){
				array[pos++] = p[i++];
			}
			else{
				array[pos++] = q[j++];
				inversions += (p.length - i);
			}
		}
		while(i < p.length)
			array[pos++] = p[i++];
		while(j < q.length)
			array[pos++] = q[j++];
			
		return inversions;
	
	}
}