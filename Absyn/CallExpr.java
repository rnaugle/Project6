package Translate.Absyn;

public class CallExpr extends Expr{
	public Expr target;
	public java.lang.String method;
	public java.util.LinkedList<Expr> args;
	public int typeIndex;
	
	public CallExpr(Expr target, String method, java.util.LinkedList<Expr> args){ 
		this.target = target;
		this.method = method;
		this.args = args;
		
		
	}
	
	 public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
