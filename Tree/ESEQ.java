package Tree;
import java.util.LinkedList;
public class ESEQ extends Exp{
	
	public Exp exp;
	public Stm stm;

	public ESEQ(Stm s, Exp e){
		exp = e;
		stm = s;
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
