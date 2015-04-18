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
		this(new NAME(target), new java.util.LinkedList<Temp.Label>());
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
		return new LinkedList<Exp>();
	}

	public Stm build(LinkedList<Exp> exps) {
		return null;
	}

}
