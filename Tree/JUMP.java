package Tree;

public class JUMP extends Stm{
	
	public Exp exp;
	public java.util.LinkedList<Translate.Temp.Label> targets;
	
	public JUMP(Exp e, java.util.LinkedList<Translate.Temp.Label> t){
		exp =e;
		targets = t;
	}
	
	public JUMP(Translate.Temp.Label target){
//		targets = new java.util.LinkedList<Translate.Temp.Label>();
//		targets.add(target);
		this(new NAME(target), new java.util.LinkedList<Translate.Temp.Label>());
	}

	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}

}
