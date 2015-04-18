package Tree;
import java.util.LinkedList;
public abstract class Stm implements Hospitable{
	public Stm(){
		
	}
	
	public LinkedList<Exp> kids() {
		return new LinkedList<Exp>();
	}

	public Stm build(LinkedList<Exp> exps) {
		return null;
	}

}
