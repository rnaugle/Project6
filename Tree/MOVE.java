package Tree;
import java.util.LinkedList;
public class MOVE extends Stm{

	public Exp dst;
	public Exp src;
	
	public MOVE(Exp d, Exp s){
		dst =d;
		src = s;
		
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
