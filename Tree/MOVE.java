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
		if (dst instanceof MEM){
			   //return new LinkedList<Exp>(((MEM)dst).exp, new ExpList(src,null));
			LinkedList<Exp> list = new LinkedList<Exp>();
			list.add(((MEM)dst).exp);
			list.add(src);
			return list;
		}
		else{
			LinkedList<Exp> list = new LinkedList<Exp>();
			//list.add(((MEM)dst).exp);
			list.add(src);
			return list;
		}
	}

	public Stm build(LinkedList<Exp> exps) {
		if (dst instanceof MEM)
			   return new MOVE(new MEM(exps.get(0)), exps.get(1));
			else return new MOVE(dst, exps.get(0));
	}
}
