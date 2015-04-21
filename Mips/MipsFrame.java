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
		FP = new Temp(30);
		formals = new LinkedList<Access>();
		actuals = new LinkedList<Access>();
		badPtr = new Label("_BADPTR");
		badSub = new Label("_BADSUB");
	}
	
	public MipsFrame(){
		//this.name = n;
		//regs = new ArrayList<InReg>();
		//for(int i = 0; i < 32; i++){
		//	(new Temp();
		//}
		maxArgOffset = 0;
		FP = new Temp();
		badPtr = new Label("_BADPTR");
		formals = new LinkedList<Access>();
		actuals = new LinkedList<Access>();
		badSub = new Label("_BADSUB");
	}
	@Override
	public Frame newFrame(Label name) {
		// TODO Auto-generated method stub
		//this.name = name;
		return new MipsFrame(name);
//		FP = new Temp(30);
//		formals = new LinkedList<Access>();
//		actuals = new LinkedList<Access>();
//		maxArgOffset = 0;
//		return this;
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
		
		int i = 4;
		boolean first = true;
		for(Access a : formals){
			if(first){
				writer.print("Formals(" + "InReg("+((InReg)a).temp+")\n");
				first = false;
			}else{
				writer.print("\tInReg("+((InReg)a).temp+")\n");
			}
			
			
		}
		writer.print("\t)\n");
		
		//i++;
		first = true;
		for(Access a : formals){
			if(i < 8){
				if(first){
					writer.print("Actuals(" + "InReg(t"+i+")\n");
					first = false;
				}else{
					writer.print("\tInReg(t"+i+")\n");
				}
				
			}
				
			else{
				writer.print("\tInFrame("+(i-4)*4+")\n");
			}
			i++;
			
		}
		
		writer.print("\t)\n");
		writer.print("BadPtr(_BADPTR)\n");
		writer.print("BadSub(_BADSUB)\n");
		writer.print("maxArgOffset("+ maxArgOffset+ ")\n");
		writer.print(")\n");
		
		
	}
	
	public void procEntryExit1(List<Tree.Stm> traced){
	//	for ( int 1= 0; i < traced.size(); i++)
	//	{
	//		procEntryExit1(traced.get(i));

	//	}
		LinkedList<Tree.Stm> traced2 = new LinkedList<Tree.Stm>();	
		
		for(int k =  formals.size()-1; k > -1; k--){
			InReg r = (InReg)formals.get(k);
			if(actuals.get(k) instanceof InReg){
				InReg r2 = (InReg)actuals.get(k);
				traced.add(0, new Tree.MOVE(new Tree.TEMP(r.temp), new Tree.TEMP(r2.temp)));
			}else{
				InFrame r2 = (InFrame)actuals.get(k);
				traced.add(0, new Tree.MOVE(new Tree.TEMP(r.temp), new Tree.MEM(/*new Tree.BINOP(Tree.BINOP.PLUS, new Tree.TEMP(new Temp(29)), */new Tree.CONST(r2.offset))))/*)*/;
			}
			
		}
		
		Temp[] temps = {new Temp(), new Temp(), new Temp(), new Temp(), new Temp(),
				new Temp(), new Temp(), new Temp(), new Temp(), new Temp()};
		
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[9]), new Tree.TEMP(new Temp(30))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[8]), new Tree.TEMP(new Temp(23))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[7]), new Tree.TEMP(new Temp(22))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[6]), new Tree.TEMP(new Temp(21))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[5]), new Tree.TEMP(new Temp(20))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[4]), new Tree.TEMP(new Temp(19))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[3]), new Tree.TEMP(new Temp(18))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[2]), new Tree.TEMP(new Temp(17))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[1]), new Tree.TEMP(new Temp(16))));
		traced.add(0, new Tree.MOVE(new Tree.TEMP(temps[0]), new Tree.TEMP(new Temp(31))));
		//traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(33)), new Tree.TEMP(new Temp(4))));
		//traced2.add(new Tree.MOVE(new Tree.TEMP(new Temp(34)), new Tree.TEMP(new Temp(5))));
		
		


		

		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(30)), new Tree.TEMP(temps[9])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(23)), new Tree.TEMP(temps[8])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(22)), new Tree.TEMP(temps[7])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(21)), new Tree.TEMP(temps[6])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(20)), new Tree.TEMP(temps[5])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(19)), new Tree.TEMP(temps[4])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(18)), new Tree.TEMP(temps[3])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(17)), new Tree.TEMP(temps[2])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(16)), new Tree.TEMP(temps[1])));
		traced.add(new Tree.MOVE(new Tree.TEMP(new Temp(31)), new Tree.TEMP(temps[0])));	

	
	}
	public Tree.Stm procEntryExit1(Tree.Stm body){
		
		return body;
	}
	public List<Assem.Instr> codeGen(List<Tree.Stm> traced) {		
		Codegen	c = new Codegen(traced, this);	
		return c.instructions;
	}
	

}
