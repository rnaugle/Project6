package Tree;

public class MEM extends Exp{
	Exp exp;
	public MEM(Exp e){
		exp = e;
	}
	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}

}
