package Tree;

public interface CodeVisitor {
	public Temp.Temp visit(BINOP n);
	public Temp.Temp visit(CALL n);
	public void visit(CJUMP n);
	public Temp.Temp visit(CONST n);
	public Temp.Temp visit(ESEQ n);
	public void visit(EXP n);
	public void visit(JUMP n);
	public void visit(LABEL n);
	public Temp.Temp visit(MEM n);
	public void visit(MOVE n);
	public Temp.Temp visit(NAME n);
	public void visit(SEQ n);
	public Temp.Temp visit(TEMP n);
}