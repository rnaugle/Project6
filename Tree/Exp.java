package Tree;
import java.util.LinkedList;
public abstract class Exp implements Hospitable{

	public Exp(){
		
	}
	public LinkedList<Exp> kids() {
		return new LinkedList<Exp>();
	}

	public Exp build(LinkedList<Exp> exps) {
		return null;
	}
	
	
	
	
}
