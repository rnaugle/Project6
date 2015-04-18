package Tree;

public class EXP extends Stm{

	Exp exp;
	public EXP(Exp e){
		exp = e;
	}
	@Override
	public void accept(IntVisitor v, int d) {
		// TODO Auto-generated method stub
		v.visit(this, d);
	}
}
