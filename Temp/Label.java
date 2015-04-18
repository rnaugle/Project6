package Temp;
import Symbol.*;
public class Label {
	public static int count;
	public String name;
	public Label(){
		name = "L" + count++;
	}
	public Label(String s){
		name = s;
	}
	public Label(Symbol s){
		//this may be incorrect
		name = s.toString();
	}
	
	public String toString(){
		return new String(name);
	}
}
