package Mips;
import Frame.*;
import Temp.Label;
import Util.*;
import Temp.*;
import java.util.LinkedList;
import java.util.List;
//import Translate.Tree.*;
public class MipsFrame extends Frame{
	public Temp FP;
	public int wordsize;
	public Label badPtr;
	public Label badSub;
	public Label name;
	public LinkedList<Access> formals;
	public LinkedList<Access> actuals;
	public LinkedList<InReg> regs;
	public int maxArgOffset;
	public static Temp[] registers = {
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
		new Temp(),
	};
	
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
		formals = new LinkedList<Access>();
		badPtr = new Label("_BADPTR");
		badSub = new Label("_BADSUB");
	}
	
	public MipsFrame(){
		//this.name = n;
		//regs = new ArrayList<InReg>();
		//for(int i = 0; i < 32; i++){
		//	(new Temp();
		//}
		badPtr = new Label("_BADPTR");
		badSub = new Label("_BADSUB");
	}
	@Override
	public Frame newFrame(Label name) {
		// TODO Auto-generated method stub
		this.name = name;
		FP = new Temp();
		formals = new LinkedList<Access>();
		maxArgOffset = 0;
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
	
	public void procEntryExit1(List<Tree.Stm> traced){
	//	for ( int 1= 0; i < traced.size(); i++)
	//	{
	//		procEntryExit1(traced.get(i));

	//	}
		LinkedList<Tree.Stm> traced2 = new LinkedList<Tree.Stm>();
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(31))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(16))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(17))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(18))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(19))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(20))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(21))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(22))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(23))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp()), new Tree.TEMP(new Temp(30))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(33)), new Tree.TEMP(new Temp(4))));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(34)), new Tree.TEMP(new Temp(5))));


		for ( int i = 0; i<traced.size(); i++)
		{
			traced2.add(procEntryExit1(traced.get(i)));
		}

		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(30)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(23)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(22)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(21)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(20)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(19)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(18)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(17)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(16)), new Tree.TEMP(new Temp())));
		traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(31)), new Tree.TEMP(new Temp())));	

		traced = traced2;	
	}
	public Tree.Stm procEntryExit1(Tree.Stm body){
		
		return body;
	}
	public List<Assem.Instr> codeGen(List<Tree.Stm> traced) {		
		Codegen	c = new Codegen(traced);	
		return c.instructions;
	}
	

}
