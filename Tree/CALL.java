package Tree;

public class CALL extends Exp{
	
	public java.util.ArrayList<Exp> args;
	public Exp func;
	
	public CALL(Exp f, java.util.ArrayList<Exp> a){
		args = a;
		func = f;
	}

	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
		
	}

}
