package Translate.Absyn;

public class ArrayExpr extends AssignableExpr{
	public Expr target, index;
	public ArrayExpr(Expr e1, Expr e2){
		this.target = e1;
		this.index = e2;
	}
	
	 public String toString()
	  {   return this.target + " [ " + this.index + " ] ";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
