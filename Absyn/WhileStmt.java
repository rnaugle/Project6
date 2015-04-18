package Translate.Absyn;

public class WhileStmt extends Stmt{
	public Expr test;
	public Stmt body;
	
	public WhileStmt(Expr test, Stmt body){
		this.test = test;
		this.body = body;
		
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
