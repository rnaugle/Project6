package Tree;
import java.util.LinkedList;
public class CJUMP extends Stm{

	public static final int	EQ	=0;
	public static final int	GE	=5;
	public static final int	GT	=3;
	public static final int	LE	=4;
	public static final int	LT	=2;
	public static final int	NE	=1;
	public static final int	UGE	=9;
	public static final int	UGT	=8;
	public static final int	ULE	=7;
	public static final int	ULT	=6;
	
	public Temp.Label iffalse;
	public Temp.Label iftrue;
	public Exp left;
	public int relop;
	public Exp right;

	public CJUMP(int rel, Exp l, Exp r, Temp.Label t, Temp.Label f){
		relop = rel;
		left = l;
		right = r;
		iftrue = t;
		iffalse = f;
	}
	
	
	public void accept(IntVisitor v) {
		v.visit(this);
		
	}

	public static int notRel(int relop)
	{
		//TODO
		return 0;
	}
	
	public void accept(CodeVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
		
	}
	public LinkedList<Exp> kids() {
		return new LinkedList<Exp>();
	}

	public Stm build(LinkedList<Exp> exps) {
		return null;
	}

}
