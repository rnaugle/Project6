package Temp;

public class Temp {

	public static int count;
	public int num;
	public Temp(){
		num = count;
		count++;
	}
	
	public Temp(int c){
		num = c;
	}
	
	public String toString(){
		return new String("t" + num);
	}
}
