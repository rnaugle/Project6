package Translate;
import Temp.Label;
import Tree.*;
import Frame.*;
import java.util.ArrayList;
public class DataFrag extends Frag{
	public String data;
	public Label label;
	//public ArrayList<Types.FUNCTION> flist;
	//public Types.CLASS c;
	
	public DataFrag(String s){
		data = s;
		//flist = new ArrayList<Types.FUNCTION>();
		label = null;
	}
	
	public DataFrag(String s, Label l){
		data = s;
		label = l;
	}
	
	
	
	public String getData(){
		return data;
	}
	
	public String toString(){
		String s;
		s = "\t.data\n";
//		if(label == null){
//			
//			s+=data+"_vtable:\n";
//			//for(Types.FUNCTION f : flist){
//				// .OBJECT o = (Types.OBJECT)f.self;
//				//Types.CLASS c = o.myClass;
//				//s+="\t.word "+c.name+"."+f.name;
//				//s+="\n";
//			//}
//		}else{

			s+= label+":\t.asciiz\t"+ data;
//		}
		return s;
	}
	

}