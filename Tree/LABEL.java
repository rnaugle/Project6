package Tree;

public class LABEL extends Stm{
	public Temp.Label label;
	
	public LABEL(Temp.Label l){
		label = l;
	}

	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
	
}
