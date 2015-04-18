package Translate.Absyn;

public class AssignableExpr extends Expr{
	
	public AssignableExpr(){
		
	
	}
	
	public String toString()
	  {   return "";   }
	
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
	 
}
