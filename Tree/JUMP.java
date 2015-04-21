package Tree;
import java.util.LinkedList;
public class JUMP extends Stm{
	
	public Exp exp;
	public java.util.LinkedList<Temp.Label> targets;
	
	public JUMP(Exp e, java.util.LinkedList<Temp.Label> t){
		exp =e;
		targets = t;
	}
	
	public JUMP(Temp.Label target){
//		targets = new java.util.LinkedList<Temp.Label>();
//		targets.add(target);
		java.util.LinkedList<Temp.Label> list = new java.util.LinkedList<Temp.Label>();
		list.add(target);
		exp = new NAME(target);
		targets = list;
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
		return new JUMP(exps.get(0),targets);
	}

}
