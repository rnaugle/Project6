package Translate.Absyn;

public class NullExpr extends Expr{
	public NullExpr(){
		
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
//	public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
