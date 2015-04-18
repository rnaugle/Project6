package Translate.Absyn;

public class ThreadDecl extends ClassDecl{
	//TODO: no clue what to do for parent
	public ThreadDecl(java.lang.String name, java.util.LinkedList<VarDecl> fields, java.util.LinkedList<MethodDecl> methods){
		super(name,null, fields, methods);
	}
	
	public String toString()
	  {   return "";   }
	public Translate.Translate.Exp accept(Translate.Translator.Translator  v) { return v.visit(this); }
	 public void accept(Translate.Visit.Visitor v)      {          v.visit(this);   }
	 //public Translate.Types.Type accept(Translate.Visit.Visitor2 v) { return v.visit(this); }
	 }
