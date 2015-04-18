package Tree;

public class TEMP extends Exp{

	public Temp.Temp temp;
	public TEMP(Temp.Temp t){
		temp = t;
	}
	@Override
	public void accept(IntVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}
}
