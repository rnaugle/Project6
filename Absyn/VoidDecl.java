package Translate.Absyn;

public class VoidDecl extends MethodDecl{
	
	//TODO: Another Constructor problem
	public VoidDecl(java.lang.String name, java.util.LinkedList<VarDecl> locals, java.util.LinkedList<Stmt> stmts){
		super(null, false, name,null,  locals, stmts, null);
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	// public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
}
