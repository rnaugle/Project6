package Translate.Absyn;

public class FieldExpr extends AssignableExpr{
	public Expr target;
	public java.lang.String field;
	public int typeIndex;
	public FieldExpr(Expr target, java.lang.String field){
		this.target = target;
		this.field = field;
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	//public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
