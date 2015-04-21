package Tree;
import java.util.LinkedList;
public class EXP extends Stm{

	public Exp exp;
	public EXP(Exp e){
		exp = e;
	}
	
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
	
	public void accept(CodeVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
		
	}
	public LinkedList<Exp> kids() {
		LinkedList<Exp> exs = new LinkedList<Exp>();
		exs.add(exp);
		return exs;
	}

	public Stm build(LinkedList<Exp> exps) {
		return new EXP(exps.get(0));
	}
}
