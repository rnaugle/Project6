package Tree;
import java.util.LinkedList;
public class TEMP extends Exp{

	public Temp.Temp temp;
	public TEMP(Temp.Temp t){
		temp = t;
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
