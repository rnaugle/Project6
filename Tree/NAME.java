package Tree;

public class NAME extends Exp{

	public Translate.Temp.Label label;
	
	public NAME(Translate.Temp.Label l){
		label = l;
	}

	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
	
}
