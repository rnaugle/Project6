package Tree;
import java.util.LinkedList;
public class MEM extends Exp{
	public Exp exp;
	public MEM(Exp e){
		exp = e;
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
		LinkedList<Exp> exs = new LinkedList<Exp>();
		exs.add(exp);
		return exs;
	}

	public Exp build(LinkedList<Exp> exps) {
		return new MEM(exps.get(0));
	}

}
