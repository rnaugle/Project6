package Translate.Absyn;

public class AddExpr extends BinOpExpr {

	public AddExpr(Expr e1, Expr e2){
		super(e1, e2);
		
		
	}
	
	 public String toString()
	  {   return this.e1 + " + " + this.e2;   }
	
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
