package Tree;
import java.util.LinkedList;
public class CALL extends Exp{
	
	public java.util.LinkedList<Exp> args;
	public Exp func;
	
	public CALL(Exp f, java.util.LinkedList<Exp> a){
		args = a;
		func = f;
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
		exs.add(func);
		exs.add(args);
		return exs;
	}

	public Exp build(LinkedList<Exp> exps) {
		return null;
	}

}
