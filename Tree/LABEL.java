package Tree;
import java.util.LinkedList;
public class LABEL extends Stm{
	public Temp.Label label;
	
	public LABEL(Temp.Label l){
		label = l;
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
		return this;
	}
	
}
