package Frame;
import Util.BoolList;
import java.util.ArrayList;
import Temp.*;
import java.util.List;

public abstract class Frame {
	abstract public Frame newFrame(Label name);
	public Label name;
	public ArrayList<Access> formals;
	abstract public Access allocLocal(boolean escape);
	abstract public Access allocFormal(boolean escape);
	abstract public void printFrame(java.io.PrintWriter writer);
	abstract public void procEntryExit1(List<Tree.Stm> traced);	
	abstract public Tree.Stm procEntryExit1(Tree.Stm body);
	abstract public List<Assem.Instr> codeGen(List<Tree.Stm> traced);
	
}
