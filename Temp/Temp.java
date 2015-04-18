package Temp;

public class Temp {

	public static int count;
	public int num;
	public Temp(){
		num = count;
		count++;
	}
	
	public String toString(){
		return new String("t" + num);
	}
}
