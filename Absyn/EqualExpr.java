package Translate.Absyn;

public class EqualExpr extends BinOpExpr{
	public EqualExpr(Expr e1, Expr e2){
		super(e1, e2);
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
