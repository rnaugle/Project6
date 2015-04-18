package Tree;

public class SEQ extends Stm{

	Stm left;
	Stm right;
	
	public SEQ(Stm l, Stm r){
		left = l;
		right = r;
	}

	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
}
