package Tree;

public class ESEQ extends Exp{
	
	public Exp exp;
	public Stm stm;

	public ESEQ(Stm s, Exp e){
		exp = e;
		stm = s;
	}
	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}

}
