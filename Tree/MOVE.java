package Tree;

public class MOVE extends Stm{

	Exp dst;
	Exp src;
	
	public MOVE(Exp d, Exp s){
		dst =d;
		src = s;
		
	}

	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
}
