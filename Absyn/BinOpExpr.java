package Translate.Absyn;

public class BinOpExpr extends Expr{
	public Expr e1, e2;
	public BinOpExpr(Expr e1, Expr e2){
		this.e1 = e1;
		this.e2 = e2;
		
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
	 
}
