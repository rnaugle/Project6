package Tree;
public class Print implements IntVisitor{
    private java.io.PrintWriter out;
    private int indent = 0;
    private Stm s;

    public Print(){this.out = new java.io.PrintWriter(System.out);}

    public Print(java.io.PrintWriter out, Stm s){
	this.out = out;
	this.s = s;
	s.accept(this);
    }

    private void printIndent(){
	for(int i=0; i<indent/2; i++)
	    out.print(" ");
    }

@Override
public void visit(BINOP n) {
// TODO Auto-generated method stub
    out.print("BINOP(");
    switch(n.binop){
	case 0:
		out.println("PLUS");
		break;
	case 1:
		out.println("MINUS");
		break;
	case 2:
		out.println("MUL");
		break;
	case 3:
		out.println("DIV");
		break;
	case 4:
		out.println("AND");
		break;
	case 5:
		out.println("OR");
		break;
	case 6:
		out.println("LSHIFT");
		break;
	case 7:
		out.println("RSHIFT");
		break;
	case 8:
		out.println("ARSHIFT");
		break;
	case 9:
		out.println("BITAND");
		break;
	case 10:
		out.println("BITOR");
		break;	
	default:
		out.println("BITXOR");
		break;
		
}
    indent +=2; printIndent();
    n.left.accept(this);
    out.println();printIndent();
    n.right.accept(this);
    indent -= 2;
    out.print(")");
}
@Override
public void visit(CALL n) {
// TODO Auto-generated method stub
    out.println("CALL(");
    indent += 2; printIndent();
    n.func.accept(this);
  
    for(int i=0; i<n.args.size(); i++){
    	  out.println();printIndent();
    	  n.args.get(i).accept(this);
	
    }
    indent -= 2;
    out.println();
    printIndent();
    out.print(")");
}
@Override
public void visit(CJUMP n) {
// TODO Auto-generated method stub
    out.print("CJUMP(");
    switch(n.relop){
    	case 0:
    		out.println("EQ");
    		break;
    	case 1:
    		out.println("NE");
    		break;
    	case 2:
    		out.println("LT");
    		break;
    	case 3:
    		out.println("GT");
    		break;
    	case 4:
    		out.println("LE");
    		break;
    	case 5:
    		out.println("GE");
    		break;
    	case 6:
    		out.println("ULT");
    		break;
    	case 7:
    		out.println("ULE");
    		break;
    	case 8:
    		out.println("UGT");
    		break;
    	default:
    		out.println("UGE");
    		break;
    		
    }
    indent += 2; printIndent();
    n.left.accept(this);
    out.println();printIndent();
    n.right.accept(this);
    out.println();;printIndent();
    out.print(n.iftrue + " ");
    //out.println();
    out.print(n.iffalse);
    indent -=2;
    out.print(")");
}
@Override
public void visit(CONST n) {
// TODO Auto-generated method stub
    out.print("CONST("+n.value+")");
}
@Override
public void visit(ESEQ n) {
// TODO Auto-generated method stub
    out.println("ESEQ(");
    indent += 2; printIndent();
    n.stm.accept(this);
    out.println();printIndent();
    n.exp.accept(this);
    indent -= 2;
    out.print(")");
}
@Override
public void visit(EXP n) {
// TODO Auto-generated method stub
    out.println("EXP(");
    indent += 2; printIndent();
    n.exp.accept(this);
    indent -= 2;
    out.print(")");
}
@Override
public void visit(JUMP n) {
// TODO Auto-generated method stub
    out.println("JUMP(");
    indent += 2; printIndent();
    n.exp.accept(this);
    //out.println();
    for(int i=0; i<n.targets.size(); i++){
    	out.println();
	n.targets.get(i).toString();
	//out.println();
    }
    indent -= 2;
    out.print(")");
}
@Override
public void visit(LABEL n) {
// TODO Auto-generated method stub
    out.print("LABEL("+n.label.name+")");
}
@Override
public void visit(MEM n) {
// TODO Auto-generated method stub
    out.println("MEM(");
    indent += 2; printIndent();
    n.exp.accept(this);
    indent -= 2;
    out.print(")");
}
@Override
public void visit(MOVE n) {
// TODO Auto-generated method stub
 out.println("MOVE(");
    indent += 2; printIndent();
    n.dst.accept(this);
    out.println();printIndent();
    n.src.accept(this);
    indent -= 2;
    out.print(")");
}
@Override
public void visit(NAME n) {
// TODO Auto-generated method stub
    out.print("NAME("+n.label.name+")");
}
@Override
public void visit(SEQ n) {
// TODO Auto-generated method stub
    out.println("SEQ(");
    indent += 2; printIndent();
    if(n.left != null){
    	n.left.accept(this);
    }
    
    out.println();printIndent();
    if(n.right!= null){
    	n.right.accept(this);
    }
    
    indent -= 2;
    out.print(")");
}
@Override
public void visit(TEMP n) {
// TODO Auto-generated method stub
 out.print("TEMP("+n.temp+")");
}
}
