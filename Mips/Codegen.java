package Mips;
import Tree.*;
public class Codegen implements CodeVisitor{
	
	public Temp.Temp visit(BINOP n){
		return null;
	}
	public Temp.Temp visit(CALL n){
		return null;
	}
	public void visit(CJUMP n){
		return;
	}
	public Temp.Temp visit(CONST n){
		return null;
	}
	public Temp.Temp visit(ESEQ n){
		return null;
	}
	public void visit(EXP n){
		return;
	}
	public void visit(JUMP n){
		return;
	}
	public void visit(LABEL n){
		return;
	}
	public Temp.Temp visit(MEM n){
		return null;
	}
	public void visit(MOVE n){
		return;
	}
	public Temp.Temp visit(NAME n){
		return null;
	}
	public void visit(SEQ n){
		return;
	}
	public Temp.Temp visit(TEMP n){
		return null;
	}
	
}