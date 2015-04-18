package Tree;

public class BINOP extends Exp{

	public static int PLUS = 0;
	public static int MINUS = 1;
	public static int MUL = 2;
	public static int DIV = 3;
	public static int AND = 4;
	public static int OR = 5;
	public static int LSHIFT = 6;
	public static int RSHIFT = 7;
	public static int ARSHIFT = 8;
	public static int BITAND = 9;
	public static int BITOR = 10;
	public static int BITXOR = 11;
	
	public int binop;
	public Exp left;
	public Exp right;
	
	public BINOP(int b, Exp l, Exp r){
		binop = b;
		left = l;
		right = r;
	
	}

	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
		
	}


}
