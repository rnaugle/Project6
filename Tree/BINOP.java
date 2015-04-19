package Tree;
import java.util.LinkedList;
public class BINOP extends Exp{

	public static final int PLUS = 0;
	public static final int MINUS = 1;
	public static final int MUL = 2;
	public static final int DIV = 3;
	public static final int AND = 4;
	public static final int OR = 5;
	public static final int LSHIFT = 6;
	public static final int RSHIFT = 7;
	public static final int ARSHIFT = 8;
	public static final int BITAND = 9;
	public static final int BITOR = 10;
	public static final int BITXOR = 11;
	
	public int binop;
	public Exp left;
	public Exp right;
	
	public BINOP(int b, Exp l, Exp r){
		binop = b;
		left = l;
		right = r;
	
	}

	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
		
	}
	
	public Temp.Temp accept(CodeVisitor v) {
		// TODO Auto-generated method stub
		return v.visit(this);
		
	}
	public LinkedList<Exp> kids() {
		return new LinkedList<Exp>();
	}

	public Exp build(LinkedList<Exp> exps) {
		return null;
	}


}
