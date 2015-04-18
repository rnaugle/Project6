package Translate.Absyn;

public class XinuCallStmt extends Stmt{
	
	
	public String method;
	public java.util.LinkedList<Expr> args;
	public XinuCallStmt(java.lang.String method, java.util.LinkedList<Expr> args){
		this.method = method;
		this.args = args;
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	// public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
