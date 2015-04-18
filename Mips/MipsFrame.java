package Mips;
import Frame.*;
import Temp.Label;
import Util.*;
import Temp.*;
import java.util.ArrayList;
//import Translate.Tree.*;
public class MipsFrame extends Frame{
	public Temp FP;
	public int wordsize;
	public Label badPtr;
	public Label badSub;
	public Label name;
	public ArrayList<Access> formals;
	public ArrayList<Access> actuals;
	public ArrayList<InReg> regs;
	
	//public MipsFrame(Label n, int numOfBools){
	//	this.name = n;
	//}
	
	public MipsFrame(Label name){
		//this.name = n;
		//regs = new ArrayList<InReg>();
		//for(int i = 0; i < 32; i++){
		//	regs.add(new InReg(new Temp()));
		//}
		this.name = name;
		FP = new Temp();
		formals = new ArrayList<Access>();
		badPtr = new Label("_BADPTR");
		badSub = new Label("_BADSUB");
	}
	
	public MipsFrame(){
		//this.name = n;
		//regs = new ArrayList<InReg>();
		//for(int i = 0; i < 32; i++){
		//	regs.add(new InReg(new Temp()));
		//}
		badPtr = new Label("_BADPTR");
		badSub = new Label("_BADSUB");
	}
	@Override
	public Frame newFrame(Label name, int k) {
		// TODO Auto-generated method stub
		this.name = name;
		FP = new Temp();
		formals = new ArrayList<Access>();
		return this;
	}
	
	//private MipsFrame(name, k){
		
	//}
	
	public Label badPtr(){
		
		return badPtr;
	}
	
	public Label badSub(){
		
		return badSub;
	}
	
	public int wordsize(){
		
		return 4;
	}

	@Override
	public Access allocLocal(boolean escape) {
		// TODO Auto-generated method stub
		Temp t = new Temp();
		InReg r = new InReg(t);
		
		return r;
	}
	
	public Access allocFormal(boolean escape) {
		// TODO Auto-generated method stub
		Temp t = new Temp();
		InReg r = new InReg(t);
		formals.add(r);
		return r;
	}
	
	public void printFrame(java.io.PrintWriter writer){
		writer.print("MipsFrame(\n");
		writer.print(name+":\n");
		writer.print("Formals(" + "InReg("+FP+")\n");
		int i = 4;
		for(Access a : formals){
			writer.print("\tInReg("+((InReg)a).temp+")\n");
			
		}
		writer.print("\t)\n");
		writer.print("Actuals(" + "InReg(t"+i+")\n");
		i++;
		for(Access a : formals){
			if(i < 8){
				writer.print("\tInReg(t"+i+")\n");
			}
				
			else{
				writer.print("\tInFrame("+(i-4)+")\n");
			}
			i++;
			
		}
		writer.print("\t)\n");
		writer.print("BadPtr(_BADPTR)\n");
		writer.print("BadSub(_BADSUB)\n");
		writer.print(")\n");
		
		
	}
	

}
