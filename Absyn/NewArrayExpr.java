package Translate.Absyn;

public class NewArrayExpr extends Expr{
	public Type type;
	public java.util.LinkedList<Expr> dimensions;
	
	public NewArrayExpr(Type type, java.util.LinkedList<Expr> dimensions){
		this.type = type;
		this.dimensions = dimensions;
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
