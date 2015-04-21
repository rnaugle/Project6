package Tree;
import java.util.LinkedList;
public class NAME extends Exp{

	public Temp.Label label;
	
	public NAME(Temp.Label l){
		label = l;
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
		return new LinkedList<Exp>();
	}

	public Exp build(LinkedList<Exp> exps) {
		return this;
	}
	
}
