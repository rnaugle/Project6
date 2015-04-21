package Tree;
import java.util.LinkedList;
public class SEQ extends Stm{

	public Stm left;
	public Stm right;
	
	public SEQ(Stm l, Stm r){
		left = l;
		right = r;
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
		throw new Error("kids() not applicable to SEQ");
	}

	public Stm build(LinkedList<Exp> exps) {
		throw new Error("build() not applicable to SEQ");
	}
}
